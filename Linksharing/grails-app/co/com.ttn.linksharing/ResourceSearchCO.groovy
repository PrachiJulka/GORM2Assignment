package com.ttn.linksharing
/*Create ResourceSearchCo which
extend searchCO and add topicId long field into it to get resource specific to topic

Create named query 'search' which takes ResourceSearchCO as argument
and find resources specific to topic id.

*/
class ResourceSearchCO extends SearchCO {

    Long topicId


}
