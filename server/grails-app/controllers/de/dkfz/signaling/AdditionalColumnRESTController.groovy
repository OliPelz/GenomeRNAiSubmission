package de.dkfz.signaling

import grails.rest.RestfulController
import org.codehaus.groovy.grails.web.servlet.HttpHeaders

import static org.springframework.http.HttpStatus.CREATED

class AdditionalColumnRESTController extends RestfulController {

    static responseFormats = ['json', 'xml']

    AdditionalColumnRESTController() {
        super(AdditionalColumn)
    }

    static allowedMethods = [get: "GET", save: "POST", update: "PUT", delete: "DELETE"]

    // we extend the save method of RestfulController to accept
    // single dataRow ids
    // which will be used to fetch the
    // DataRow obj , store the id of the additionalColumn in it and
    // save it and for the response of a POST
    @Override
    def save() {
        def dataRowId = request.JSON?.dataRowId

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

        if(dataRowId != null) {
            DataRow myRow = DataRow.get(dataRowId)
            myRow.additionalColumns.add(instance)
            myRow.save flush : true
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
