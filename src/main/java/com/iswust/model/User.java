package com.iswust.model;

import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;



public class User implements Serializable {
    private Integer id;
    private String account;
    private String password;
    private String photo;
    private String name;
    private Integer type;
    private Integer ban;
    private List<Post> posts;
    private List<Message> messages;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", ban=" + ban +
                ", posts=" + posts +
                ", messages=" + messages +
                '}';
    }

    public User(Integer id, String account, String password, String photo, String name, Integer type, Integer ban, List<Post> posts, List<Message> messages) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.photo = photo;
        this.name = name;
        this.type = type;
        this.ban = ban;
        this.posts = posts;
        this.messages = messages;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setBan(Integer ban) {
        this.ban = ban;
    }

    public Integer getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }

    public Integer getBan() {
        return ban;
    }
}