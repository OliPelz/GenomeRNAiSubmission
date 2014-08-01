package genomernaisubmission

import grails.rest.RestfulController

class AdditionalColumnRESTController extends RestfulController {

    static responseFormats = ['json', 'xml']

    AdditionalColumnRESTController() {
        super(AdditionalColumn)
    }

    static allowedMethods = [get: "GET", save: "POST", update: "PUT", delete: "DELETE"]
}
