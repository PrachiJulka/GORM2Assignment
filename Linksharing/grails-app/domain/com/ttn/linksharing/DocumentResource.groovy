package com.ttn.linksharing
//Add toString for linkresource with url
class DocumentResource extends Resource {

    String filePath

    static constraints = {
        filePath(nullable: false,blank: false)
    }

    @Override
    public String toString() {
        return "DocumentResource{" +
                "filePath='" + filePath + '\'' +
                '}';
    }
}
