package com.ttn.linksharing

import org.hibernate.ObjectNotFoundException

/*
-Add search action in a resource controller,
 which will search if q parameter is set and it will set visibility of
 resourcesearchco to public-Add search action in a resource controller, which will search
if q parameter is set and it will set visibility of resourcesearchco to public
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
}
