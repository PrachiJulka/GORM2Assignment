package com.ttn.linksharing
/*
Add resource show action and get trending topics also

    - Public Topic with maximum resources is considered as a trending topic
    - Create static method getTrendingTopics in Topic domain which will return list of TopicVO
    - TopicVO will have id,name,visibility,count,createdBy fields
    - Use createalias and groupproperty in criteria
    - Use count for getting count of resources of a topic
    - Use multiple order statement first one ordered by resource count and second one ordered by topic name
    -Maximum 5 records should be shown
    - Topic with maximum resource should come first
*/

class TopicVO {

    Integer id
    String name
    Visibility visibility
    Integer count
    User createdBy

}
