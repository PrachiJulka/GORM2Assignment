package com.ttn.linksharing

/*
Add top post when user is not logged in

    - Resource with maximum number of votes will be top post
    -Only 5 posts should be shown in order of maximum vote count
    -Use groupProperty with id of resource otherwise lots of queries will be fired
    -Collect Resource list with resource id using getall rather then finder otherwise ordering will not be maintained

*/
abstract class Resource {
    String description
    Date dateCreated
    Date lastUpdated
    static belongsTo = [user: User, topic: Topic]
    static hasMany = [resourceRating: ResourceRating, readingItems: ReadingItem]
    static constraints = {
        description(type: 'text')
    }
     RatingInfoVO ratingInfoVO

    static transients = ['ratingInfoVO']
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

    Integer totalVotes(Resource resource) {
        Integer votes = ResourceRating.createCriteria().count() {

            eq("resource", resource)
        }

        return votes
    }

    def avgScore(Resource resource){
        def average= ResourceRating.createCriteria().get {
            projections {
                avg('score')
            }
            eq("resource",resource)
        }

        return average

    }
    def totalScore(Resource resource){
        def sum1 = ResourceRating.createCriteria().get(){

            projections {
                sum('score')
            }
            eq("resource",resource)
        }

        return sum1
    }

    RatingInfoVO getRatingInfoVo(Resource resource){
        RatingInfoVO ratingInfoVO1=new RatingInfoVO()
        ratingInfoVO1.averagescore=avgScore(resource)
        ratingInfoVO1.totalScore=totalScore(resource)
        ratingInfoVO1.totalVotes=totalVotes(resource)
        return ratingInfoVO1
    }

/*
    Add top post when user is not logged in

    - Resource with maximum number of votes will be top post
    -Only 5 posts should be shown in order of maximum vote count
    -Use groupProperty with id of resource otherwise lots of queries will be fired
    -Collect Resource list with resource id using getall rather then finder otherwise ordering will not be maintained
*/

    List<Resource> topPost(){

        List resourceIds = ResourceRating.createCriteria().list {
            projections {
                property('resource.id')
            }
            groupProperty('resource.id')
            count('resource.id', 'resourceCount')
            order('resourceCount', 'desc')
            maxResults(5)
        }

        List<Resource> resources = Resource.getAll(resourceIds)
        return resources



    }

}





