package com.ttn.linksharing
/*
Add test case for document resource and linkresource tostring*/

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class LinkResourceSpec extends Specification implements DomainUnitTest<LinkResource> {

    def setup() {
        mockDomain(User)
        mockDomain(Topic)
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            false == false
    }

    def "url field should contain valid url"(){
        setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email,userName:"prachiJ", confirmPassword: password, password:password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
        Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)

        when:
        LinkResource linkResource=new LinkResource(url:"www.google.com", user:user,topic: topic,description: "aaaaaaa")

        topic.addToResources(linkResource)
        user.addToTopics(topic)
        linkResource.validate()
        user.save()

        then:
        User.count==1

        when:
        LinkResource linkResource1=new LinkResource(url:"www", user:user,topic: topic,description: "aaaaaaa")

        topic.addToResources(linkResource1)
        user.addToTopics(topic)
        user.addToResources(linkResource1)
        linkResource1.validate()
        user.save()

        then:
        linkResource1.errors.getFieldErrorCount('url')==1

    }

    def "check to string of linkresource"(){
        setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email,userName:"prachiJ",password:password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
        Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)
        LinkResource linkResource=new LinkResource(url:"www.google.com", user:user,topic: topic,description: "aaaaaaa")

        when:
        linkResource.save()
        then:
        linkResource.toString()==
                "LinkResource{url='${linkResource.url}'}"

    }


}
