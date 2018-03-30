package com.ttn.linksharing

//Create update action which takes an id and serious
// as a parameter if subscription and seriousness found,
// then save else render not found, if saved then render success else errors
class SubscriptionController {

    def index() { }


    def save(Long id){
        Topic topic=Topic.read(id)
        Subscription subscription=new Subscription(user: session.user,topics:topic)
        if(subscription.save())
            render("success")
        else
            render("error")

    }

    def delete(Integer id){
        println(id)
        Subscription subscription=Subscription.load(id)
        if(subscription!=null)
        {
            subscription.delete(flush:true)
            render("success")
        }
        else
            render("not found")

    }

    def update(Integer id,String serious){
        Subscription subscription=Subscription.findByIdAndSeriousness(id,Seriousness.valueOf(serious))
        if(subscription!=null)
        {
            if(subscription.save(flush:true))
            render("success")
            else
                render("failure")
        }
        else
            render("not found")
    }
}
