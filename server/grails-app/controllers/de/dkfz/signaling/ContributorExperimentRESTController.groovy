package de.dkfz.signaling

import grails.rest.RestfulController

class ContributorExperimentRESTController extends RestfulController {

    static responseFormats = ['json', 'xml']

    ContributorExperimentRESTController() {
        super(ContributorExperiment)
    }

    static allowedMethods = [get: "GET", save: "POST", update: "PUT", delete: "DELETE"]
}
