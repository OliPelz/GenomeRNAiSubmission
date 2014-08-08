package de.dkfz.signaling

import grails.rest.RestfulController
import org.codehaus.groovy.grails.web.json.JSONArray
import org.codehaus.groovy.grails.web.servlet.HttpHeaders

import static org.springframework.http.HttpStatus.CREATED

class ContributorExperimentRESTController extends RestfulController {

    static responseFormats = ['json', 'xml']

    ContributorExperimentRESTController() {
        super(ContributorExperiment)
    }

    static allowedMethods = [get: "GET", save: "POST", update: "PUT", delete: "DELETE"]

// we extend the save method of RestfulController to accept
    // a list of dataRow ids  ([1,2,3,..])
    // which will be used to fetch all associated dataRows objs
    //  to save and for the response of a POST
    @Override
    def save() {
        if (handleReadOnly()) {
            return
        }
        def instance = createResource()

        instance.validate()
        if (instance.hasErrors()) {
            respond instance.errors, view: 'create' // STATUS CODE 422
            return
        }
        instance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: "${resourceName}.label".toString(), default: resourceClassName), instance.id])
                redirect instance
            }
            '*' {
                response.addHeader(HttpHeaders.LOCATION,
                        g.createLink(
                                resource: this.controllerName, action: 'show', id: instance.id, absolute: true,
                                namespace: hasProperty('namespace') ? this.namespace : null))
                respond instance, [status: CREATED]
            }
        }
    }
}
