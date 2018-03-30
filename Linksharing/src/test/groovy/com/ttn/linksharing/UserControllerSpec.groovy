package com.ttn.linksharing

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification
//Update test case for usercontroller index action
// Add show action for topic which will take id as a parameter
class UserControllerSpec extends Specification implements ControllerUnitTest<UserController>,DomainUnitTest<User> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == true
    }

    void "when index is called it should display username"(){
        given:
        User user = new User(email: "prachijulka@gmail.com",confirmPassword:"ROOT@123" ,userName: "PrachiJulka", password: "ROOT@123",
                firstName: "Prachi", lastName: "Julka", admin: false, active: true, photo: 101)

        user.save(flush: true)
        session["user"]=user
        when:
        controller.index()
        then:
        response.contentAsString=="PrachiJulka"


    }
}
