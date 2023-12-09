package ru.msu608.twitterapp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.msu608.twitterapp.models.TwitterAccountMessage;
import ru.msu608.twitterapp.models.TwitterPostMessage;
import ru.msu608.twitterapp.repositories.TwitterMessageRepository;

import java.util.List;

@RestController
public class TwitterAppController {
    private final TwitterMessageRepository twitterRepository;

    public TwitterAppController(@Qualifier("hazelcastCachedTwitterRepository") TwitterMessageRepository twitterRepository) {
        this.twitterRepository = twitterRepository;
    }

    @GetMapping("/users")
    public List<TwitterAccountMessage> findAllUsers(){
        return twitterRepository.findAllUsers();
    }

    @GetMapping("/posts")
    public List<TwitterPostMessage> findAllPosts(){
        return twitterRepository.findAllPosts();
    }
    @GetMapping("/users/{Id}")
    public List<TwitterAccountMessage> findUserById( @PathVariable("Id") String accountId){
        return twitterRepository.findUserById(accountId);
    }
    @GetMapping("/posts/{PostId}")
    public List<TwitterPostMessage> findPostById( @PathVariable("PostId") String postId){
        return twitterRepository.findPostById(postId);
    }
    @GetMapping("/postsOfUser/{UserId}")
    public List<TwitterPostMessage> findPostsOfUser( @PathVariable("UserId") String userId){
        return twitterRepository.findPostsOfUser(userId);
    }

    @PostMapping("/users")
    public TwitterAccountMessage saveUser(@RequestBody TwitterAccountMessage user){
        return twitterRepository.saveUser(user);
    }

    @PostMapping("/posts")
    public TwitterPostMessage savePost(@RequestBody TwitterPostMessage post){
        return twitterRepository.savePost(post);
    }

    @GetMapping("/users/by_id/{id}")
    public TwitterAccountMessage findUserBy_id(@PathVariable("id") String _id){
        return twitterRepository.findUserBy_id(_id);
    }

    @GetMapping("/posts/by_id/{id}")
    public TwitterPostMessage findPostBy_id(@PathVariable("id") String _id){
        return twitterRepository.findPostBy_id(_id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") String id){
        twitterRepository.deleteUserById(id);
    }
    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable("id") String id) {
        twitterRepository.deletePostById(id);
    }
    @PutMapping("/users")
    public TwitterAccountMessage putUser(@RequestBody TwitterAccountMessage user){
        return twitterRepository.putUser(user);
    }
    @PutMapping("/posts")
    public TwitterPostMessage putPost(@RequestBody TwitterPostMessage post){
        return twitterRepository.putPost(post);
    }
}
