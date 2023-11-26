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

    public TwitterAccountMessage saveUser(TwitterAccountMessage user){
        TwitterAccountMessage newUser = new TwitterAccountMessage(null,
                user.getRegistrationDate(), user.getUserId(), user.getFullName(),
                user.getScore(), user.getLastOnline());
        return mongoTemplate.save(newUser, COLLECTION_USER_NAME);
    }

    public TwitterPostMessage savePost(TwitterPostMessage post){
        TwitterPostMessage newPost = new TwitterPostMessage(null, post.getCreationDate(), post.getLikes(),
                post.getAuthorId(), post.getPostId(), post.getPostType(), post.getText());
        return mongoTemplate.save(newPost, COLLECTION_POST_NAME);
    }

    public TwitterAccountMessage findUserBy_id(String _id){
        return mongoTemplate.findById(_id, TwitterAccountMessage.class, COLLECTION_USER_NAME);
    }

    public TwitterPostMessage findPostBy_id(String _id){
        return mongoTemplate.findById(_id, TwitterPostMessage.class, COLLECTION_POST_NAME);
    }
    public void deleteUserById(String userId){
        Query query = new Query().addCriteria(Criteria.where("Id").is(userId));
        mongoTemplate.remove(query, COLLECTION_USER_NAME);
    }
    public void deletePostById(String postId){
        Query query = new Query().addCriteria(Criteria.where("Id").is(postId));
        mongoTemplate.remove(query, COLLECTION_POST_NAME);
    }
    public TwitterAccountMessage putUser(TwitterAccountMessage user){
        List<TwitterAccountMessage> existedUser = findUserById(user.getUserId());
        if(!existedUser.isEmpty()){
            if(existedUser.get(0).equals(user)) {
                return existedUser.get(0);
            }
            else {
                deleteUserById(existedUser.get(0).getUserId());
            }
        }
        return saveUser(user);
    }

    public TwitterPostMessage putPost(TwitterPostMessage post){
        List<TwitterPostMessage> existingPost = findPostById(post.getPostId());
        if(!existingPost.isEmpty()){
            if(existingPost.get(0).equals(post)) {
                return existingPost.get(0);
            }
            else {
                deletePostById(existingPost.get(0).getPostId());
            }
        }
        return savePost(post);
    }

}
