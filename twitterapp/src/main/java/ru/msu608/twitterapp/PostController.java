package ru.msu608.twitterapp;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.msu608.twitterapp.models.TwitterPostMessage;
import ru.msu608.twitterapp.repositories.PostRepository;

@RestController
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/post/{id}")
    public TwitterPostMessage findById(@PathVariable("id") String id) {
        return postRepository.getById(id);
    }

    @GetMapping("/post")
    public List<TwitterPostMessage> search(@RequestParam("query") String query) {
        return postRepository.search(query);
    }



}
