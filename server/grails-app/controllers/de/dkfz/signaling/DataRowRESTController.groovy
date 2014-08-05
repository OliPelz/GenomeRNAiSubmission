package de.dkfz.signaling

import grails.rest.RestfulController

class DataRowRESTController extends RestfulController {

    static responseFormats = ['json', 'xml']

    DataRowRESTController() {
        super(DataRow)
    }

    static allowedMethods = [get: "GET", save: "POST", update: "PUT", delete: "DELETE"]

    @Override
    protected getObjectToBind() {
        def contExpId = params.contExpId
        if(contExpId) {
            def contributeExp = ContributorExperiment.get(contExpId)
            request.parameterMap.put('contributorExperiment', contributeExp)
        }
        request
    }
}
