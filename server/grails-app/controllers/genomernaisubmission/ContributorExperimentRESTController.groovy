package genomernaisubmission

import grails.rest.RestfulController

class ContributorExperimentRESTController extends RestfulController {

    static responseFormats = ['json', 'xml']

    ContributorExperimentRESTController() {
        super(ContributorExperiment)
    }

    static allowedMethods = [get: "GET", save: "POST", update: "PUT", delete: "DELETE"]

    @Override
    def getObjectToBind() {
	def contributorExpObj = Author.get(params.contExpId)
        request.parameterMap.put('contributorExperiment', contributorExperiment)
        return request
    }
}
