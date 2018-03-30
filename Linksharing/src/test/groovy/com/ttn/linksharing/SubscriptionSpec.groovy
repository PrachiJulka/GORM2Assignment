package com.ttn.linksharing

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class SubscriptionSpec extends Specification implements DomainUnitTest<Subscription> {

    def setup() {
        mockDomain User
       // mockDomain Topic
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
        false == false
    }
    def "Seriousness should not be null"(){

        setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email,userName:"prachiJ",password:password,confirmPassword: password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
        Topic topic=new Topic(name:"finance",visibility: Visibility.PUBLIC,createdBy: user)

        when:
        Subscription subscription=new Subscription(seriousness:Seriousness.CASUAL, user:user,topics:topic)
        subscription.validate()
        topic.addToSubscriptions(subscription)
        user.addToTopics(topic)
        user.addToSubscriptions(subscription)
        user.save(flush:true)

        then:
        User.count==1

        when:
        Subscription subscription1=new Subscription(seriousness:null, user:user,topics:topic)
        subscription1.validate()
        topic.addToSubscriptions(subscription1)
        user.addToTopics(topic)
        user.addToSubscriptions(subscription1)
        user.save(flush:true)

        then:
        subscription1.errors.getFieldErrorCount('seriousness')==1
        subscription1.errors.hasErrors()==true

    }

    def "User should not be able to subscribe to topic multiple times "(){
        setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email,userName:"prachiJ",password:password, confirmPassword: password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
        Topic topic=new Topic(name:"finance",visibility: Visibility.PUBLIC,createdBy: user)

        when:
        Subscription subscription=new Subscription(seriousness:Seriousness.CASUAL, user:user,topics:topic)
        subscription.validate()
        topic.addToSubscriptions(subscription)
        user.addToTopics(topic)
        user.addToSubscriptions(subscription)
        user.save(flush:true)

        Subscription subscription1=new Subscription(seriousness:Seriousness.SERIOUS, user:user,topics:topic)
        subscription1.validate()
        topic.addToSubscriptions(subscription1)
        user.addToTopics(topic)
        user.addToSubscriptions(subscription1)
        user.save(flush:true)


        then:
        println(subscription1.errors.getAllErrors())
        subscription1.errors.getFieldErrorCount('topics')==1
        subscription1.errors.hasErrors()==true
    }


    def "Topic should not be null"(){
        setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email,userName:"prachiJ",password:password,confirmPassword: password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
        Topic topic=new Topic(name:"finance",visibility: Visibility.PUBLIC,createdBy: user)

        when:
        Subscription subscription=new Subscription(seriousness:Seriousness.CASUAL, user:user,topics:null)
        subscription.validate()
        topic.addToSubscriptions(subscription)
        user.addToTopics(topic)
        user.addToSubscriptions(subscription)
        user.save(flush:true)

        then:
        subscription.errors.getFieldErrorCount('topics')==1
        subscription.errors.hasErrors()==true
    }

    def "User should not be null"(){
        setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email,userName:"prachiJ",password:password,confirmPassword: password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
        Topic topic=new Topic(name:"finance",visibility: Visibility.PUBLIC,createdBy: user)

        when:
        Subscription subscription=new Subscription(seriousness:Seriousness.CASUAL, user:null,topics:null)
        subscription.validate()
        topic.addToSubscriptions(subscription)
        user.addToTopics(topic)
        user.addToSubscriptions(subscription)
        user.save(flush:true)

        then:
        subscription.errors.getFieldErrorCount('user')==1
        subscription.errors.hasErrors()==true
    }
}
