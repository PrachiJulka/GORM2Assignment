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
    Add resource show action and get trending topics also

    - Public Topic with maximum resources is considered as a trending topic
    - Create static method getTrendingTopics in Topic domain which will return list of TopicVO
    - TopicVO will have id,name,visibility,count,createdBy fields
    - Use createalias and groupproperty in criteria
    - Use count for getting count of resources of a topic
    - Use multiple order statement first one ordered by resource count and second one ordered by topic name
    -Maximum 5 records should be shown
    - Topic with maximum resource should come first
*/






    static List<TopicVO> getTrendingTopics(){
         List topicList = Resource.createCriteria().list {
            projections {
                createAlias('topic', 't')
                groupProperty('t.id')
                property('t.name')
                property('t.visibility')
                count('t.id', 'topicCount')
                property('t.createdBy')
            }
            eq('t.visibility',Visibility.PUBLIC)
            order('topicCount', 'desc')
            order('t.name', 'asc')
            maxResults(5)
        }
      //  return topics


        List topicVOList = []
        topicList.each {

                topicVOList.add(new TopicVO(id: it[0], name: it[1], visibility: it[2], count: it[3], createdBy: it[4]))


        }
        return topicVOList



    }  /*Integer id
    String name
    Visibility visibility
    Integer count
    User Created*/


    @Override
    public String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                '}';
    }
}




