package com.ttn.linksharing


import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ResourceRatingSpec extends Specification implements DomainUnitTest<ResourceRating> {

    def setup() {
        mockDomain(User)
       // mockDomain(Topic)
        mockDomain(Resource)
        mockDomain(LinkResource)
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            false == false
    }
    def "minimum and maximum score of rating should be 1 and 5 respectively"(){
       setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email,userName:"prachiJ",confirmPassword: password, password:password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
        Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)
        Resource resource=new LinkResource(url: "www.google.com",description: "abhabhab",user: user,topic: topic)


        when:
        ResourceRating resourceRating=new ResourceRating(score: 1,user: user,resource: resource)
        resource.addToResourceRating(resourceRating)

        resourceRating.validate()
        resourceRating.save()

        then:
        ResourceRating.count==1


        when:
        ResourceRating resourceRating1=new ResourceRating(score: 5,user: user,resource: resource)
        resource.addToResourceRating(resourceRating1)

        resourceRating1.validate()
        //resourceRating1.save()
        resourceRating1.save(flush:true)
        then:
        ResourceRating.count==2


        when:
        ResourceRating resourceRating2=new ResourceRating(score:0,user: user,resource: resource)
        resource.addToResourceRating(resourceRating2)
        resourceRating2.validate()
        resourceRating2.save()




        then:
       resourceRating2.errors.getFieldErrorCount('score')==1
        resourceRating2.errors.hasErrors()==true


        when:
        ResourceRating resourceRating3=new ResourceRating(score:6,user: user,resource: resource)
        resource.addToResourceRating(resourceRating3)
        resourceRating3.validate()
        resourceRating3.save()




        then:
        resourceRating3.errors.getFieldErrorCount('score')==1
        resourceRating3.errors.hasErrors()==true
    }

    def "user should not have null "(){
        setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email,userName:"prachiJ",password:password,confirmPassword: password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
        Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)
        Resource resource=new LinkResource(url: "www.google.com",description: "abhabhab",user: user,topic: topic)

        when:
        ResourceRating resourceRating=new ResourceRating(score:2,user: null,resource:resource)
        resource.addToResourceRating(resourceRating)
        resourceRating.validate()
        resourceRating.save()

        then:
        resourceRating.errors.getFieldErrorCount('user')==1
        resourceRating.errors.hasErrors()==true
  }


    def "resource should not be null "(){
        setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email,userName:"prachiJ",password:password,confirmPassword: password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
        Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)

        when:
        ResourceRating resourceRating=new ResourceRating(score:2,user: user,resource:null)
        resourceRating.validate()
        resourceRating.save()

        then:
        resourceRating.errors.hasErrors()==true
    }
    def "Resource rating can be given by a user only one time for a resource "(){
        setup:
        String email = "prachijulka@tothenew.com"
        String password = 'p1231'
        User user = new User(email: email,userName:"prachiJ",password:password,confirmPassword: password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
        Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)
        Resource resource=new LinkResource(url:"www.yahoo.com",description: "abhabhab",user: user,topic: topic)
        when:
        ResourceRating resourceRating1=new ResourceRating(score:2,user: user,resource:resource)
         resource.addToResourceRating(resourceRating1)
        resource.save()
        user.addToResourceRating(resourceRating1)
        user.save(flush:true)
        ResourceRating resourceRating2=new ResourceRating(score:3,user:user,resource: resource)
        resource.addToResourceRating(resourceRating2)
        resource.save()
        user.addToResourceRating(resourceRating2)
        user.save(flush:true)
        resourceRating2.validate()
        resourceRating2.save()

        then:
        resource.errors.hasErrors()==true
        user.errors.hasErrors()==true
        resourceRating2.errors.hasErrors()==true

    }

   def "resource rating score should not be null "(){
       setup:
       String email = "prachijulka@tothenew.com"
       String password = 'p1231'
       User user = new User(email: email,userName:"prachiJ",password:password,confirmPassword: password, firstName: "Prachi", lastName: "Julka",admin:false,active:true)
       Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)
       Resource resource=new LinkResource(url:"www.yahoo.com",description: "abhabhab",user: user,topic: topic)

       when:
       ResourceRating resourceRating=new ResourceRating(score:null,user: user,resource:resource)
       resourceRating.validate()
       resourceRating.save()

       then:
       resourceRating.errors.getFieldErrorCount('score')==1

   }

    def "normal"(){

        when:
        User user = new User(email: "prachijulka31@gmail.com",userName:"prachiJ",password:"123456h", confirmPassword: "123456h", firstName: "Prachi", lastName: "Julka",admin:false,active:true)
        Topic topic = new Topic(name:"sd",visibility: Visibility.PUBLIC,createdBy: user)
        Resource resource1=new LinkResource(url:"www.yahoo.com",description: "awawawa",user: user,topic:topic)
        Resource resource=new LinkResource(url: "www.google.com",description: "abhabhab",user: user,topic: topic)


        ResourceRating resourceRating1=new ResourceRating(score:2,user: user,resource:resource)
        resource.addToResourceRating(resourceRating1)
        resourceRating1.validate()
        resourceRating1.save()
        //resource.save(flush:true)



        ResourceRating resourceRating =new ResourceRating(score: 4,user:user,resource: resource1)
        resource1.addToResourceRating(resourceRating)
        resourceRating.validate()
        resourceRating.save()

        then:
        ResourceRating.count==2
    }
}
