package com.ttn.linksharing

import grails.testing.gorm.DomainUnitTest

import spock.lang.Specification

/*
//Create a method in visibility enum to convert string into enum and write test case for the same
*/

class TopicSpec extends Specification implements DomainUnitTest<Topic>{

    def setup() {
        mockDomain User
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
        false == false
    }
    def "Topic name should be unique per user"() {
        setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email,userName:"prachiJ",confirmPassword: password, password:password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)

        when:
        Topic topic = new Topic()
        topic.createdBy = user
        topic.name = "topic"

        topic.visibility = Visibility.PRIVATE
      //  topic.save(flush:true)
        user.addToTopics(topic)
        user.save(flush:true)

        Topic topic1 = new Topic() 
        topic1.name = "topic"
        topic1.createdBy = user
        topic1.visibility = Visibility.PUBLIC
        user.addToTopics(topic1)
        user.validate()

        then:
        user.errors.hasErrors()==true

    }


    def "Topic name should not be null or blank"(){

        setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email,confirmPassword: password, userName:"prachiJ",password:password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)

        when:
        Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)
        user.addToTopics(topic)
        user.save(flush:true)

        then:
        Topic.count==1

        when:
        Topic topic1 = new Topic(name: null,createdBy: user,visibility: Visibility.PUBLIC)
        user.addToTopics(topic1)
       // user.validate()
        topic1.validate()
        user.save(flush:true)



        then:
     //   topic1.errors.getFieldErrorCount('name')==1
        topic1.errors.getFieldErrorCount('name')==1
        user.errors.hasErrors() == true



        when:
        Topic topic2 = new Topic(name: "",createdBy: user,visibility: Visibility.PUBLIC)
        user.addToTopics(topic1)
        // user.validate()
        topic2.validate()
        user.save(flush:true)



        then:
        //   topic1.errors.getFieldErrorCount('name')==1
        topic2.errors.getFieldErrorCount('name')==1
        user.errors.hasErrors() == true


    }

    def "Visiblity of topic should not be null"(){

        given:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email, confirmPassword: password, userName:"prachiJ",password:password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)

        when:
        Topic topic=new Topic(name:"topic",createdBy: user,visibility:null)
        user.addToTopics(topic)
        topic.validate()
        user.save()
        then:
        topic.errors.getFieldErrorCount('visibility')==1
        user.errors.hasErrors()==true
    }

    def "Created by should not be null"(){


        when:
        Topic topic=new Topic (name:"topic",createdBy: null,visibility: Visibility.PUBLIC)
        topic.validate()
        topic.save()

        then:
        topic.errors.getFieldErrorCount('createdBy')
        topic.errors.hasErrors()==true

    }

    def "To check to string "(){

            setup:
            String email = "prachijulka@tothenew.com"
            String password = 'p1231'
            User user = new User(email: email,userName:"prachiJ",password:password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
            Topic topic=new Topic (name:"topic",createdBy: null,visibility: Visibility.PUBLIC)

        when:
            topic.save()

            then:
            topic.toString()==
                    "Topic{name='${topic.name}'}"




    }
    def "String value should be converted to enum"(){
        given:
        String PUBLIC="PUBLIC"
        when:
        Visibility visibility=Visibility.stringToEnum(PUBLIC)

        then:
        visibility==Visibility.PUBLIC
    }

    def "String value should be converted to seriousness Enum"(){
        when:
       Seriousness seriousness= Seriousness.stringToEnum("casual")

        then:
        seriousness==Seriousness.CASUAL
    }

}