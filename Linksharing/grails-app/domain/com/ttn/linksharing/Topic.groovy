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


/*
    List<User> users = User.createCriteria().list() {
        ilike("firstName", "${q}%")
        ilike("address", "%${q}")
        le("age", age)
    }
    return users
*/


    static List<Topic> getTrendingTopics(){
        List<Topic> topics = Topic.createCriteria().list() {
            projections {
                max('resources')
            }
            eq('visibility',Visibility.PUBLIC)
        }
        return topics
    }
    @Override
    public String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                '}';
    }
}




