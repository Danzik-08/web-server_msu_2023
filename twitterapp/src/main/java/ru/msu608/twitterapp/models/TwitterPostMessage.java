package ru.msu608.twitterapp.models;

import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TwitterPostMessage implements Serializable {
    @BsonId
    public String _id; // Unused
    @Field("CreationDate")
    public LocalDateTime creationDate;
    @Field("Score")
    public Integer likes;
    @Field("OwnerUserId")
    public Integer authorId;
    @Field("Id")
    public Integer postId;
    @Field("PostTypeId")
    public String postType;
    @Field("Body")
    public String text;

    public TwitterPostMessage() {
    }

    public TwitterPostMessage(String _id, LocalDateTime creationDate,
                              Integer likes, Integer authorId,
                              Integer postId, String postType,
                              String text) {
        this._id = _id;
        this.creationDate = creationDate;
        this.likes = likes;
        this.authorId = authorId;
        this.postId = postId;
        this.postType = postType;
        this.text = text;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
