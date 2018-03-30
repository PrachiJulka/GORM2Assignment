package com.ttn.linksharing

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class LoginCheckInterceptorSpec extends Specification implements InterceptorUnitTest<LoginCheckInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test loginCheck interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"loginCheck")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
