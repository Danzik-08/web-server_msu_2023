package ru.msu608.twitterapp.repositories;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu608.twitterapp.models.TwitterAccountMessage;
import ru.msu608.twitterapp.models.TwitterPostMessage;

import java.util.List;

@Repository
public class TwitterMessageRepository {
    private static final String COLLECTION_USER_NAME = "twitterUsers";
    private static final String COLLECTION_POST_NAME = "twitterPosts";
    private final MongoTemplate mongoTemplate;

    public TwitterMessageRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<TwitterAccountMessage> findAllUsers(){
        return mongoTemplate.find(new Query().limit(50), TwitterAccountMessage.class, COLLECTION_USER_NAME);
    }
    public List<TwitterPostMessage> findAllPosts(){
        return mongoTemplate.find(new Query().limit(50), TwitterPostMessage.class, COLLECTION_POST_NAME);
    }
    public List<TwitterAccountMessage> findUserById(String accountId){
        Query query = new Query().addCriteria(Criteria.where("Id").is(accountId));
        return mongoTemplate.find(query, TwitterAccountMessage.class, COLLECTION_USER_NAME);
    }
    public List<TwitterPostMessage> findPostById(String postId){
        Query query = new Query().addCriteria(Criteria.where("Id").is(postId));
        return mongoTemplate.find(query, TwitterPostMessage.class, COLLECTION_POST_NAME);
    }

    public List<TwitterPostMessage> findPostsOfUser(String userId){
        if(findUserById(userId).isEmpty()) // there is no such user
            return List.of();
        Query query = new Query().addCriteria(Criteria.where("OwnerUserId").is(userId));
        return mongoTemplate.find(query, TwitterPostMessage.class, COLLECTION_POST_NAME);
    }
}
