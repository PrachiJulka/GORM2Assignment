package com.ttn.linksharing

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class ApplicatinInterceptorSpec extends Specification implements InterceptorUnitTest<ApplicatinInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test applicatin interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"applicatin")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
