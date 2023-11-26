package ru.msu608.twitterapp.models;

import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class TwitterPostMessage implements Serializable {
    @BsonId
    public String _id; // Unused
    @Field("CreationDate")
    public String creationDate;
    @Field("Score")
    public String likes;
    @Field("OwnerUserId")
    public String authorId;
    @Field("Id")
    public String postId;
    @Field("PostTypeId")
    public String postType;
    @Field("Body")
    public String text;

    public TwitterPostMessage() {
    }

    public TwitterPostMessage(String _id, String creationDate,
                              String likes, String authorId,
                              String postId, String postType,
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwitterPostMessage that = (TwitterPostMessage) o;
        return Objects.equals(creationDate, that.creationDate) && Objects.equals(likes, that.likes) && Objects.equals(authorId, that.authorId) && Objects.equals(postId, that.postId) && Objects.equals(postType, that.postType) && Objects.equals(text, that.text);
    }
}
