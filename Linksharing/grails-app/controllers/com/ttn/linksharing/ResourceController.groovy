package com.ttn.linksharing

import org.hibernate.ObjectNotFoundException

/*
-Call this method from resource show action
*/
class ResourceController {

    def index() { }


    def delete(Integer id){

        Resource resource=Resource.load(id)
        println resource
    }
    def handleObjectNotFoundException(ObjectNotFoundException e) {

        render ("no object found")
    }
    def search(){
        ResourceSearchCO resourceSearchCO=new ResourceSearchCO()
        if(resourceSearchCO.q)
            resourceSearchCO.visibility=Visibility.PUBLIC
    }
    def show(Integer id){
        Resource resource=Resource.get(id)
        RatingInfoVO ratingInfoVO= resource.getRatingInfoVo(resource)
        render(ratingInfoVO.averagescore)
        println Topic.getTrendingTopics()
    }
    def handleNullPointerException(NullPointerException e) {

        render ("null found")
    }
}
