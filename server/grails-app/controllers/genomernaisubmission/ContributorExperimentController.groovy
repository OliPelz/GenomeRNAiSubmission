package genomernaisubmission


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ContributorExperimentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ContributorExperiment.list(params), model: [contributorExperimentInstanceCount: ContributorExperiment.count()]
    }

    def show(ContributorExperiment contributorExperimentInstance) {
        respond contributorExperimentInstance
    }

    def create() {
        respond new ContributorExperiment(params)
    }

    @Transactional
    def save(ContributorExperiment contributorExperimentInstance) {
        if (contributorExperimentInstance == null) {
            notFound()
            return
        }

        if (contributorExperimentInstance.hasErrors()) {
            respond contributorExperimentInstance.errors, view: 'create'
            return
        }

        contributorExperimentInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'contributorExperimentInstance.label', default: 'ContributorExperiment'), contributorExperimentInstance.id])
                redirect contributorExperimentInstance
            }
            '*' { respond contributorExperimentInstance, [status: CREATED] }
        }
    }

    def edit(ContributorExperiment contributorExperimentInstance) {
        respond contributorExperimentInstance
    }

    @Transactional
    def update(ContributorExperiment contributorExperimentInstance) {
        if (contributorExperimentInstance == null) {
            notFound()
            return
        }

        if (contributorExperimentInstance.hasErrors()) {
            respond contributorExperimentInstance.errors, view: 'edit'
            return
        }

        contributorExperimentInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ContributorExperiment.label', default: 'ContributorExperiment'), contributorExperimentInstance.id])
                redirect contributorExperimentInstance
            }
            '*' { respond contributorExperimentInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ContributorExperiment contributorExperimentInstance) {

        if (contributorExperimentInstance == null) {
            notFound()
            return
        }

        contributorExperimentInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ContributorExperiment.label', default: 'ContributorExperiment'), contributorExperimentInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'contributorExperimentInstance.label', default: 'ContributorExperiment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
