package genomernaisubmission

import grails.rest.RestfulController

class DataRowRESTController extends RestfulController {

    static responseFormats = ['json', 'xml']

    DataRowRESTController() {
        super(DataRow)
    }
    static allowedMethods = [get: "GET", save: "POST", update: "PUT", delete: "DELETE"]
}
