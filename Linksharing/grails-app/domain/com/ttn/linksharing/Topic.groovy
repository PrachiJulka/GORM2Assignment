package com.ttn.linksharing
/*Use eager fetching for topic and user in subscription*/

class Topic {

    String name
    Date dateCreated
    Date lastUpdated
    Visibility visibility
    //List<Resource> resourceList
    static belongsTo = [ createdBy : User]
    static hasMany = [subscriptions:Subscription, resources:Resource]
    static constraints = {
        name(blank: false, nullable: false, unique: 'createdBy' )
        visibility(nullable: false)
    }
    static mapping = {
        sort("name": "asc")
        subscriptions lazy: false
    }

    def afterInsert() {
        log.info "----------Into After Insert------"
        Topic.withNewSession {
            Subscription subscription= new Subscription(topics: this,seriousness: Seriousness.VERYSERIOUS,user: this.createdBy)
            subscription.validate()
            log.error("Topic ${subscription.errors.getFieldErrors()}")

            subscription.save()
        }


    }


    @Override
    public String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                '}';
    }
}




