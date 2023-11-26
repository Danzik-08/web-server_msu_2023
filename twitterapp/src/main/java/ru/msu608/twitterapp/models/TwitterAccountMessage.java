package ru.msu608.twitterapp.models;

import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

public class TwitterAccountMessage implements Serializable {
    @BsonId
    public String _id; // Unused
    @Field("CreationDate")
    public String registrationDate;
    @Field("Id")
    public String userId;
    @Field("DisplayName")
    public String fullName;
    @Field("Reputation")
    public String score;
    @Field("LastAccessDate")
    public String lastOnline;

    public TwitterAccountMessage() {
    }

    public TwitterAccountMessage(String _id, String registrationDate,
                                 String userId, String fullName,
                                 String score, String lastOnline) {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(String lastOnline) {
        this.lastOnline = lastOnline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwitterAccountMessage that = (TwitterAccountMessage) o;
        return Objects.equals(registrationDate, that.registrationDate) && Objects.equals(userId, that.userId) && Objects.equals(fullName, that.fullName) && Objects.equals(score, that.score) && Objects.equals(lastOnline, that.lastOnline);
    }
}
