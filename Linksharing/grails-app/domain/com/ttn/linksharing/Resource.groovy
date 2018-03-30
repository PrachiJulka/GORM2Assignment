package com.ttn.linksharing

//-Create named query 'search' which takes
// ResourceSearchCO as argument and find resources specific to topic id.
abstract class Resource {
    String description
    Date dateCreated
    Date lastUpdated
    static belongsTo = [user: User, topic: Topic]
    static hasMany = [resourceRating: ResourceRating, readingItems: ReadingItem]
    static constraints = {
        description(type: 'text')
    }
    /*
    -Updated Resource search named query and add condition
    to search topic with specified visibility
     */
    static namedQueries = {
        search {
             ResourceSearchCO resourceSearchCO ->
            if(resourceSearchCO.topicId)
                    eq('topic.id', resourceSearchCO.topicId)
            if(resourceSearchCO.visibility)
                eq('topic.visibility',resourceSearchCO.visibility)

        }
     }
    }





