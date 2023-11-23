package ru.msu608.twitterapp.models;

public class TwitterAccountMessage implements TwitterMessage{
    public String name;
    public Integer age;
    public String sex;
    public String status;

    public TwitterAccountMessage() {
    }

    public TwitterAccountMessage(String name, Integer age, String sex, String status) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
