package com.ttn.linksharing

class ResourceRating {

    Integer score

    static belongsTo = [resource:Resource,user:User]
    static constraints = {
        score( validator: {
            return it >= 1 &&  it<= 5
        })
        user(nullable: false,blank: false)
        resource(nullable: false,blank:false,unique: 'user')

    }
}
