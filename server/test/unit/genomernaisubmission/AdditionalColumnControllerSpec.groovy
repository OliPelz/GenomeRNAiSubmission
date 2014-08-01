package genomernaisubmission


import grails.test.mixin.*
import spock.lang.*

@TestFor(AdditionalColumnController)
@Mock(AdditionalColumn)
class AdditionalColumnControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.additionalColumnInstanceList
        model.additionalColumnInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.additionalColumnInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        def additionalColumn = new AdditionalColumn()
        additionalColumn.validate()
        controller.save(additionalColumn)

        then: "The create view is rendered again with the correct model"
        model.additionalColumnInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        additionalColumn = new AdditionalColumn(params)

        controller.save(additionalColumn)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/additionalColumn/show/1'
        controller.flash.message != null
        AdditionalColumn.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def additionalColumn = new AdditionalColumn(params)
        controller.show(additionalColumn)

        then: "A model is populated containing the domain instance"
        model.additionalColumnInstance == additionalColumn
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def additionalColumn = new AdditionalColumn(params)
        controller.edit(additionalColumn)

        then: "A model is populated containing the domain instance"
        model.additionalColumnInstance == additionalColumn
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/additionalColumn/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def additionalColumn = new AdditionalColumn()
        additionalColumn.validate()
        controller.update(additionalColumn)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.additionalColumnInstance == additionalColumn

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        additionalColumn = new AdditionalColumn(params).save(flush: true)
        controller.update(additionalColumn)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/additionalColumn/show/$additionalColumn.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/additionalColumn/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def additionalColumn = new AdditionalColumn(params).save(flush: true)

        then: "It exists"
        AdditionalColumn.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(additionalColumn)

        then: "The instance is deleted"
        AdditionalColumn.count() == 0
        response.redirectedUrl == '/additionalColumn/index'
        flash.message != null
    }
}
