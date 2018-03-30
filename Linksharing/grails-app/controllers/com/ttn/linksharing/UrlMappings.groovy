package com.ttn.linksharing

class UrlMappings {

    HashMap msp;
    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

       // "/"(view:"/index")
        "/"(view: "/login/")
        "/"(view:"/login/index")
         "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
