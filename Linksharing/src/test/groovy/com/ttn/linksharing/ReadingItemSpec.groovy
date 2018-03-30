package com.ttn.linksharing

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
/*
Add test case for document resource and linkresource tostring*/

class ReadingItemSpec extends Specification implements DomainUnitTest<ReadingItem> {

    def setup() {
        mockDomain(User)
        mockDomain(Topic)
        mockDomain(Resource)
        mockDomain(LinkResource)
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            false == false
    }

    def "Readingitem resource should be unique per user"(){
        setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email,userName:"prachiJ",confirmPassword: password, password:password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
        Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)
        Resource resource=new LinkResource(url: "www.google.com",description: "abhabhab",user: user,topic: topic)


        when:
        ReadingItem readingItem=new ReadingItem(isRead: true,user:user,resource:resource)
       readingItem.validate()
        readingItem.save()

        then:
        ReadingItem.count==1


        when:

        ReadingItem readingItem1=new ReadingItem(isRead: true,user:user,resource:resource)
        resource.addToReadingItems(readingItem1)
        resource.save()
        user.addToReadingItems(readingItem1)
        user.save()
        readingItem1.validate()
        readingItem1.save()
        ReadingItem readingItem2=new ReadingItem(isRead: true,user:user,resource:resource)
        resource.addToReadingItems(readingItem2)
        resource.save()
        user.addToReadingItems(readingItem2)
        user.save()
        readingItem2.validate()
        readingItem2.save()

       then:
        readingItem2.errors.hasErrors()==true

    }

    def "isRead should not be null"(){
        setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email,userName:"prachiJ",password:password,confirmPassword: password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
        Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)
        Resource resource=new LinkResource(url: "www.google.com",description: "abhabhab",user: user,topic: topic)


        when:
        ReadingItem readingItem=new ReadingItem(isRead: null,user:user,resource:resource)

        readingItem.validate()
        readingItem.save()
        then:
        readingItem.errors.getFieldErrorCount('isRead')==1



    }
    def "user should not be null"() {
        setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email, userName: "prachiJ", password: password,confirmPassword: password, firstName: "Prachi", lastName: "Julka", admin: false, active: true)
        Topic topic = new Topic(name: "sd", visibility: Visibility.PUBLIC, createdBy: user)
        Resource resource = new LinkResource(url: "www.google.com", description: "abhabhab", user: user, topic: topic)


        when:
        ReadingItem readingItem = new ReadingItem(isRead: true, user: null, resource: resource)

        readingItem.validate()
        readingItem.save()
        then:
        readingItem.errors.getFieldErrorCount('user') == 1
    }




        def "resource should not be null"(){
            setup:
            String email = "prachijulka@tothenew.com"
            String password = 'p1231'
            User user = new User(email: email,userName:"prachiJ",password:password,confirmPassword: password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
            Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)
            Resource resource=new LinkResource(url: "www.google.com",description: "abhabhab",user: user,topic: topic)


            when:
            ReadingItem readingItem=new ReadingItem(isRead: true,user:user,resource:null)

            readingItem.validate()
            readingItem.save()
            then:
            readingItem.errors.getFieldErrorCount('resource')==1



        }
}
