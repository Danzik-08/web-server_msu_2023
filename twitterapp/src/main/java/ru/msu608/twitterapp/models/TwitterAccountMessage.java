package ru.msu608.twitterapp.models;

import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

public class TwitterAccountMessage implements Serializable {
    @BsonId
    public String _id; // Unused
    @Field("CreationDate")
    public String registrationDate;
    @Field("Id")
    public Integer userId;
    @Field("DisplayName")
    public String fullName;
    @Field("Reputation")
    public Integer score;
    @Field("LastAccessDate")
    public String lastOnline;

    public TwitterAccountMessage() {
    }

    public TwitterAccountMessage(String _id, String registrationDate,
                                 Integer userId, String fullName,
                                 Integer score, String lastOnline) {
        this._id = _id;
        this.registrationDate = registrationDate;
        this.userId = userId;
        this.fullName = fullName;
        this.score = score;
        this.lastOnline = lastOnline;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(String lastOnline) {
        this.lastOnline = lastOnline;
    }
}
