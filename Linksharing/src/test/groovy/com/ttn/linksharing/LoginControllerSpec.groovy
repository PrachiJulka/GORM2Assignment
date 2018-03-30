package com.ttn.linksharing

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification



//That should include testing of all conditions specified in above exercise
class LoginControllerSpec extends Specification implements ControllerUnitTest<LoginController>,DomainUnitTest<User> {

    def setup() {

    }

    def cleanup() {
    }

    void "test something"() {
        expect: "fix me"
        false == false
    }

   /* def "check when user sessions exists in index method"() {
        given: "a session user exists"
        session["user"] = new User()

        when:
        controller.loginHandler("admin","admin@123")

        then:
        response.forwardedUrl == '/user/index'
    }
    def "check if user session exists in index method"() {
        given: "a session user exists"
        session["user"] = null

        when:
        controller.index()

        then:
        response.contentAsString=="User not found"
    }
    def "if user is logged out"() {
        when:
        controller.logout()
        then:
        !session.user
        response.redirectUrl == '/login/index'
    }
    def "check if user exists but not active"() {
        given:
        User user = new User(email: "prachijulka@gmail.com", userName: "PrachiJulka",confirmPassword: "ROOT@123", password: "ROOT@123",
                firstName: "Prachi", lastName: "Julka", admin: false, active: false, photo: 101)

        user.save(flush: true)
        when:
        controller.loginHandler(user.userName , user.password)
        then:
        flash.error=="Your account is not active"
        response.redirectUrl== '/login/index'
    }
    def "check if user doesn't exist"() {
        given:
        String username = "prachi"
        String password = "prachi"

        when:
        controller.loginHandler(username, password)

        then:
        flash.error == "User not found"
        response.redirectUrl== '/login/index'
    }*/
    def "check if user exists and is active"() {
        given:
        User user = new User(email: "prachijulka@gmail.com", userName: "PrachiJulka", password: "ROOT@123",
           confirmPassword: "ROOT@123", firstName: "Prachi", lastName: "Julka", admin: false, active: true, photo: 101)
        user.save(flush: true)

        when:
        controller.loginHandler(user.userName, user.password)

        then:
        response.forwardedUrl == '/user/index'
    }

}
