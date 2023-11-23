package ru.msu608.twitterapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.msu608.twitterapp.models.TwitterAccountMessage;
import ru.msu608.twitterapp.models.TwitterMessage;
import ru.msu608.twitterapp.models.TwitterPostMessage;
import ru.msu608.twitterapp.repositories.TwitterMessageRepository;

import java.util.List;

@RestController
public class TwitterAppController {
    private final TwitterMessageRepository twitterRepository;

    public TwitterAppController(TwitterMessageRepository twitterRepository) {
        this.twitterRepository = twitterRepository;
    }

    @GetMapping("/records")
    public List<TwitterAccountMessage> findAll(){
        return twitterRepository.findAll();
    }
}
