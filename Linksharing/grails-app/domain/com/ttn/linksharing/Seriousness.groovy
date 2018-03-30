package com.ttn.linksharing
//Create static method in seriuosness
// which take string as parameter and returns seriousness, it should be case insensitive
enum Seriousness {

    SERIOUS,VERYSERIOUS,CASUAL

    static Seriousness stringToEnum(String seriousness){
        if(seriousness==seriousness.toLowerCase())
        return Seriousness.valueOf(seriousness.toUpperCase())
        else
            return Seriousness.valueOf(seriousness)

    }
}