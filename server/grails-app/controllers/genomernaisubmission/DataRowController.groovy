package genomernaisubmission


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DataRowController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DataRow.list(params), model: [dataRowInstanceCount: DataRow.count()]
    }

    def show(DataRow dataRowInstance) {
        respond dataRowInstance
    }

    def create() {
        respond new DataRow(params)
    }

    @Transactional
    def save(DataRow dataRowInstance) {
        if (dataRowInstance == null) {
            notFound()
            return
        }

        if (dataRowInstance.hasErrors()) {
            respond dataRowInstance.errors, view: 'create'
            return
        }

        dataRowInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataRowInstance.label', default: 'DataRow'), dataRowInstance.id])
                redirect dataRowInstance
            }
            '*' { respond dataRowInstance, [status: CREATED] }
        }
    }

    def edit(DataRow dataRowInstance) {
        respond dataRowInstance
    }

    @Transactional
    def update(DataRow dataRowInstance) {
        if (dataRowInstance == null) {
            notFound()
            return
        }

        if (dataRowInstance.hasErrors()) {
            respond dataRowInstance.errors, view: 'edit'
            return
        }

        dataRowInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DataRow.label', default: 'DataRow'), dataRowInstance.id])
                redirect dataRowInstance
            }
            '*' { respond dataRowInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(DataRow dataRowInstance) {

        if (dataRowInstance == null) {
            notFound()
            return
        }

        dataRowInstance.delete flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DataRow.label', default: 'DataRow'), dataRowInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataRowInstance.label', default: 'DataRow'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
