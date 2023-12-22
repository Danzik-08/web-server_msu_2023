package ru.msu608.twitterapp.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Repository;
import ru.msu608.twitterapp.models.TwitterPostMessage;

@Repository
public class PostRepository {

    private final RestHighLevelClient elasticSearchClient;

    public PostRepository(RestHighLevelClient elasticSearchClient) {
        this.elasticSearchClient = elasticSearchClient;
    }

    public List<TwitterPostMessage> search(String query) {
        SearchRequest searchRequest = new SearchRequest("ru.stackoverflow.com").source(new SearchSourceBuilder()
                .query(QueryBuilders.simpleQueryStringQuery(query)));
        try {
            SearchHit[] hits = elasticSearchClient.search(searchRequest, RequestOptions.DEFAULT).getHits().getHits();
            return Arrays.stream(hits).map( hit ->
                    new TwitterPostMessage(hit.getId(), String.valueOf(hit.getSourceAsMap().get("CreationDate")), String.valueOf(hit.getSourceAsMap().get("UpVotes")), String.valueOf(hit.getSourceAsMap().get("OwnerUserId")), "", "", String.valueOf(hit.getSourceAsMap().get("Body")))
            ).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    public TwitterPostMessage getById(String postId) {
        try {
            GetResponse response = elasticSearchClient.get(new GetRequest("ru.stackoverflow.com", postId), RequestOptions.DEFAULT);
            if (!response.isExists()) {
                return null;
            }
            Map<String, Object> source = response.getSourceAsMap();
            System.out.print(source);
            Object text = source.get("Body");
            String text_ = "null";
            if (text != null) {
                text_ = text.toString();
            }
            return new TwitterPostMessage(response.getId(), String.valueOf(source.get("CreationDate")), String.valueOf(source.get("UpVotes")), String.valueOf(source.get("OwnerUserId")), "", String.valueOf(source.get("PostTypeId")), text_);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
