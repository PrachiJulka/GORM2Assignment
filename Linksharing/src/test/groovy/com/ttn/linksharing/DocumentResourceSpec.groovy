package com.ttn.linksharing

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class DocumentResourceSpec extends Specification implements DomainUnitTest<DocumentResource> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            false == false
    }

    def "filePath should not be null or blank"(){
        setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email,userName:"prachiJ",password:password,confirmPassword: password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
        Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)

        when:
        DocumentResource documentResource=new DocumentResource(filePath: "prachi/file.txt", user:user,topic: topic,description: "aaaaaaa")
        user.addToResources(documentResource)
        topic.addToResources(documentResource)
        documentResource.validate()
        user.save()

        then:
        User.count==1

        when:
        DocumentResource documentResource1=new DocumentResource(filePath: " ", user:user,topic: topic,description: "aaaaaaa")
        user.addToResources(documentResource1)
        topic.addToResources(documentResource1)
        documentResource1.validate()
        user.save()

        then:
        documentResource1.errors.getFieldErrorCount('filePath')==1

        when:
        DocumentResource documentResource2=new DocumentResource(filePath: null, user:user,topic: topic,description: "aaaaaaa")
        user.addToResources(documentResource2)
        topic.addToResources(documentResource2)
        documentResource2.validate()
        user.save()

        then:
        documentResource2.errors.getFieldErrorCount('filePath')==1

    }

    def "check to string of documentResource"(){
        setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email,userName:"prachiJ",password:password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
        Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)
        DocumentResource documentResource2=new DocumentResource(filePath: "dfvndkjvndknvkdf", user:user,topic: topic,description: "aaaaaaa")

        when:
        documentResource2.save()
        then:
        documentResource2.toString()==
                "DocumentResource{filePath='${documentResource2.filePath}'}"

    }

}
