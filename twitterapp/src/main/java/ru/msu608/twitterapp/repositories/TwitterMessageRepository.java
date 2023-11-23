package ru.msu608.twitterapp.repositories;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import ru.msu608.twitterapp.models.TwitterAccountMessage;
import ru.msu608.twitterapp.models.TwitterMessage;
import ru.msu608.twitterapp.models.TwitterPostMessage;

import java.util.List;

@Repository
public class TwitterMessageRepository {
    private static final String COLLECTION_NAME = "twitter";
    private final MongoTemplate mongoTemplate;

    public TwitterMessageRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<TwitterAccountMessage> findAll(){
        return mongoTemplate.findAll(TwitterAccountMessage.class, COLLECTION_NAME);
    }
}
