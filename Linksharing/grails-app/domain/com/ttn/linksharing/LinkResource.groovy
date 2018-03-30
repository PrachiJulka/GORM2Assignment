package com.ttn.linksharing
//Add toString for linkresource with url
class LinkResource extends Resource {

    String url

    static constraints = {
        url(url: true,nullable:false,blank: false)
    }


    @Override
    public String toString() {
        return "LinkResource{" +
                "url='" + url + '\'' +
                '}';
    }
}
