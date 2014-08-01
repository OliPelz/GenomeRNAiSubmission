package genomernaisubmission


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AdditionalColumnController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AdditionalColumn.list(params), model: [additionalColumnInstanceCount: AdditionalColumn.count()]
    }

    def show(AdditionalColumn additionalColumnInstance) {
        respond additionalColumnInstance
    }

    def create() {
        respond new AdditionalColumn(params)
    }

    @Transactional
    def save(AdditionalColumn additionalColumnInstance) {
        if (additionalColumnInstance == null) {
            notFound()
            return
        }

        if (additionalColumnInstance.hasErrors()) {
            respond additionalColumnInstance.errors, view: 'create'
            return
        }

        additionalColumnInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'additionalColumnInstance.label', default: 'AdditionalColumn'), additionalColumnInstance.id])
                redirect additionalColumnInstance
            }
            '*' { respond additionalColumnInstance, [status: CREATED] }
        }
    }

    def edit(AdditionalColumn additionalColumnInstance) {
        respond additionalColumnInstance
    }

    @Transactional
    def update(AdditionalColumn additionalColumnInstance) {
        if (additionalColumnInstance == null) {
            notFound()
            return
        }

        if (additionalColumnInstance.hasErrors()) {
            respond additionalColumnInstance.errors, view: 'edit'
            return
        }

        additionalColumnInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'AdditionalColumn.label', default: 'AdditionalColumn'), additionalColumnInstance.id])
                redirect additionalColumnInstance
            }
            '*' { respond additionalColumnInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(AdditionalColumn additionalColumnInstance) {

        if (additionalColumnInstance == null) {
            notFound()
            return
        }

        additionalColumnInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'AdditionalColumn.label', default: 'AdditionalColumn'), additionalColumnInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'additionalColumnInstance.label', default: 'AdditionalColumn'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
