package com.ttn.linksharing
//Use eager fetching for topic and user in subscription
class Subscription {
    Date dateCreated
    Seriousness seriousness
    static belongsTo = [topics:Topic,user:User]

    static mapping = {
        seriousness defaultValue: Seriousness.SERIOUS
        topics fetch:'join'
        user fetch: 'join'
    }
    static constraints = {
        seriousness(nullable:false)
        topics(nullable: false, unique:'user')
        user(nullable: false)
    }

}
