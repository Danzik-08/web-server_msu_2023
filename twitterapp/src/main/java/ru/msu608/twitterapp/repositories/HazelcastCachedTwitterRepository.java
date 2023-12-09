package ru.msu608.twitterapp.repositories;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu608.twitterapp.models.TwitterAccountMessage;
import ru.msu608.twitterapp.models.TwitterPostMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class HazelcastCachedTwitterRepository implements TwitterMessageRepository{
    
    private final TwitterMessageRepository delegate;

    // private final Map<String, Message> cache = new ConcurrentHashMap<>();
    private final IMap<String, List<TwitterAccountMessage>> messageUserCache;
    private final IMap<String, List<TwitterPostMessage>> messagePostCache;

    public HazelcastCachedTwitterRepository(@Qualifier("mongoTwitterMessageRepository") TwitterMessageRepository delegate,
                                            @Autowired HazelcastInstance hazelcastInstanceUser,
                                            @Autowired HazelcastInstance hazelcastInstancePost){
        this.delegate = delegate;
        messageUserCache = hazelcastInstanceUser.getMap("user");
        messagePostCache = hazelcastInstancePost.getMap("post");
    }

    @Override
    public List<TwitterAccountMessage> findAllUsers(){
        return delegate.findAllUsers();
    }
    
    @Override
    public List<TwitterPostMessage> findAllPosts(){
        return delegate.findAllPosts();
    }

    @Override
    public List<TwitterAccountMessage> findUserById(String accountId){
        // if (cache.containsKey(accountId)){
        //     return cache.get(accountId);
        // }
        // TwitterAccountMessage account = delegate.findUserById(accountId);
        // cache.put(account.getId(), account);

        // return account;

        return messageUserCache.computeIfAbsent(accountId, delegate::findUserById);
    }

    @Override
    public List<TwitterPostMessage> findPostById(String postId){
        // if (cache.containsKey(postId)){
        //     return cache.get(postId);
        // }
        // TwitterPostMessage post = delegate.findPostById(postId);
        // cache.put(post.getId(), post);
        
        // return post;

        return messagePostCache.computeIfAbsent(postId, delegate::findPostById);

    }

    @Override
    public List<TwitterPostMessage> findPostsOfUser(String userId){
        // if (cache.containsKey(userId)){
        //     return cache.get(userId);
        // }
        // TwitterPostMessage post = delegate.findPostsOfUser(userId);
        // cache.put(post.getId(), post);
        
        // return post;
        return messagePostCache.computeIfAbsent(userId, delegate::findPostsOfUser);
    }

    @Override
    public TwitterAccountMessage saveUser(TwitterAccountMessage user){
        TwitterAccountMessage updated = delegate.saveUser(user);
        messageUserCache.put(user.get_id(), List.of(user));
        return updated;
    }

    @Override
    public TwitterPostMessage savePost(TwitterPostMessage post){
        TwitterPostMessage updated = delegate.savePost(post);
        messagePostCache.put(post.get_id(), List.of(post));
        return updated;
    }

    @Override
    public TwitterAccountMessage findUserBy_id(String _id){
        if (messageUserCache.containsKey(_id)){
            return messageUserCache.get(_id).get(0);
        }
        TwitterAccountMessage user = delegate.findUserBy_id(_id);
        messageUserCache.put(user.get_id(), List.of(user));

        return user;
    }
    
    @Override
    public TwitterPostMessage findPostBy_id(String _id){
        if (messagePostCache.containsKey(_id)){
             return messagePostCache.get(_id).get(0);
        }
        TwitterPostMessage post = delegate.findPostBy_id(_id);
        messagePostCache.put(post.get_id(), List.of(post));

        return post;
    }


    @Override
    public void deleteUserById(String userId){
        messageUserCache.remove(userId);
        delegate.deleteUserById(userId);
    }    

    @Override
    public void deletePostById(String postId){
        messagePostCache.remove(postId);
        delegate.deletePostById(postId);
    }    
    
    @Override
    public TwitterAccountMessage putUser(TwitterAccountMessage user){
        messageUserCache.remove(user.get_id());
        messageUserCache.put(user.get_id(), List.of(user));
        return delegate.putUser(user);
    }    
    
    @Override
    public TwitterPostMessage putPost(TwitterPostMessage post){
        messagePostCache.remove(post.get_id());
        messagePostCache.put(post.get_id(), List.of(post));
        return delegate.putPost(post);
    }

}