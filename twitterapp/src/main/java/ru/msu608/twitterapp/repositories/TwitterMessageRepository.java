package ru.msu608.twitterapp.repositories;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu608.twitterapp.models.TwitterAccountMessage;
import ru.msu608.twitterapp.models.TwitterPostMessage;

import java.util.List;

public interface TwitterMessageRepository {
    
    public List<TwitterAccountMessage> findAllUsers();

    public List<TwitterPostMessage> findAllPosts();
    
    public List<TwitterAccountMessage> findUserById(String accountId);

    public List<TwitterPostMessage> findPostById(String postId);

    public List<TwitterPostMessage> findPostsOfUser(String userId);

    public TwitterAccountMessage saveUser(TwitterAccountMessage user);

    public TwitterPostMessage savePost(TwitterPostMessage post);

    public TwitterAccountMessage findUserBy_id(String _id);

    public TwitterPostMessage findPostBy_id(String _id);

    public void deleteUserById(String userId);

    public void deletePostById(String postId);

    public TwitterAccountMessage putUser(TwitterAccountMessage user);

    public TwitterPostMessage putPost(TwitterPostMessage post);

}