package com.ttn.linksharing

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification
//Write the test cases for subscription save, update, delete functionality.
class SubscriptionControllerSpec extends Specification implements ControllerUnitTest<SubscriptionController>,DomainUnitTest<Subscription> {
   /* Topic topic=Topic.read(id)
    Subscription subscription=new Subscription(user: session.user,topics:topic)
    if(subscription.save())
    render("success")
    else
    render("error")*/
    def setup() {
    mockDomain(User)
        mockDomain(Topic)

    }


    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == true
    }

    def "data should be saved on save action"(){
        given:
        User user = new User(email: "prachijulka@gmail.com", userName: "PrachiJulka", password: "ROOT@123",confirmPassword: "ROOT@123",
                firstName: "Prachi", lastName: "Julka", admin: false, active: true, photo: 101)
        Topic topic=new Topic(createdBy: user, name: "topic",visibility: Visibility.PUBLIC)

        session.user=user
        topic.save(flush:true ,failOnError:true)
        /*user.addToTopics(topic)
        user.save()*/

        when:
        controller.save(topic.id)

        then:
        response.contentAsString=="error"


    }
}
