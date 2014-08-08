package de.dkfz.signaling

import grails.converters.JSON
import grails.rest.RestfulController
import org.codehaus.groovy.grails.web.servlet.HttpHeaders

import static org.springframework.http.HttpStatus.CREATED

class DataRowRESTController extends RestfulController {

    static responseFormats = ['json', 'xml']

    DataRowRESTController() {
        super(DataRow)
    }

    static allowedMethods = [get: "GET", save: "POST", update: "PUT", delete: "DELETE"]

    // we extend the save method of RestfulController to accept
    // single contExpId ids and lists of additional column ids
    // which will be used to fetch the
    // ContributorExperiment obj and the additionalColumn objs to save and for the response of a POST
    @Override
    def save() {
        def contExpId = request.JSON?.contExpId
        def ContributorExperiment exp
        //only if we havent provided a contribtorexperiment obj
        if (contExpId != null && request.JSON?.contributorExperiment == null) {
            exp = ContributorExperiment.get(contExpId)
        }
        if (handleReadOnly()) {
            return
        }
        def instance = createResource()

        instance.validate()
        if (instance.hasErrors()) {
            respond instance.errors, view: 'create' // STATUS CODE 422
            return
        }
        if (exp != null) {
            instance.contributorExperiment = exp
        }
        instance.save flush: true


        if(exp != null) {
             exp.dataRows.add(instance)
             exp.save flush : true
        }


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