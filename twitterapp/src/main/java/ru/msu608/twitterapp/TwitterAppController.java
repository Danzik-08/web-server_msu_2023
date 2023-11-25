package ru.msu608.twitterapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.msu608.twitterapp.models.TwitterAccountMessage;
import ru.msu608.twitterapp.models.TwitterPostMessage;
import ru.msu608.twitterapp.repositories.TwitterMessageRepository;

import java.util.List;

@RestController
public class TwitterAppController {
    private final TwitterMessageRepository twitterRepository;

    public TwitterAppController(TwitterMessageRepository twitterRepository) {
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
}
