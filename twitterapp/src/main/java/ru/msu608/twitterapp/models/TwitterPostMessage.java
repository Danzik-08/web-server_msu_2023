package ru.msu608.twitterapp.models;

import java.util.Date;

public class TwitterPostMessage implements TwitterMessage{
    public Date date = new Date();
    public String text;
    public TwitterPostMessage() {
    }
    public TwitterPostMessage(Date date, String text) {
        this.date = date;
        this.text = text;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
