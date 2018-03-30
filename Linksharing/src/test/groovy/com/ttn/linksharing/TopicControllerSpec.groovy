package com.ttn.linksharing

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification
//Write the test case for the Topic save.
class TopicControllerSpec extends Specification implements ControllerUnitTest<TopicController> ,DomainUnitTest<Topic>{


    def setup() {
       mockDomain(User)
    }

    def cleanup() {
    }


      def "topic should be saved on save action"() {
          given:
          User user = new User(email: "prachijulka@gmail.com", confirmPassword: "ROOT@123", userName: "PrachiJulka", password: "ROOT@123",
                  firstName: "Prachi", lastName: "Julka", admin: false, active: true, photo: 101)

              session.user=user
          Topic topic=new Topic(name: "topic20",visibility:Visibility.PUBLIC,createdBy: session.user)
          when:
          controller.save(topic, "Casual")

          then:
          println(topic.createdBy)
          response.contentAsString == "saved Successfully"


      }
    void "test something"() {
        expect:"fix me"
        true == true
    }
    def "Delete topic"(){
        given:
        User user = new User(email: "prachijulka@gmail.com", userName: "PrachiJulka",confirmPassword: "ROOT@123", password: "ROOT@123",
                firstName: "Prachi", lastName: "Julka", admin: false, active: true, photo: 101)
        session.user=user
        Topic topic=new Topic(name: "topic20",visibility:Visibility.PUBLIC,createdBy: session.user)
        topic.save(flush:true)
        user.addToTopics(topic)
        user.save()
        println(topic.id)
        when:
        controller.delete(topic.id)
        then:
        response.contentAsString=="sucess"

    }

}
