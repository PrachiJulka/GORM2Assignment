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
    -Create named query 'search' which takes ResourceSearchCO
     as argument and find resources specific to topic id.
     List<Account> accounts =
      Account.search(co).list(max:co.max,order:co.order,sort:co.sort,offset:co.offset)
     */
    static namedQueries = {
        search {
            ResourceSearchCO resourceSearchCO ->
                eq 'topic.id', resourceSearchCO.topicId


        }
    }
}




