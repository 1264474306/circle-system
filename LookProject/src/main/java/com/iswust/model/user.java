package com.iswust.model;

import org.springframework.scheduling.support.SimpleTriggerContext;

public class user {
    private Integer id;
    private String account;
    private String password;
    private String photo;
    private String name;
    private String type;
    private Integer ban;

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", ban=" + ban +
                '}';
    }

    public user(Integer id, String account, String password, String photo, String name, String type, Integer ban) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.photo = photo;
        this.name = name;
        this.type = type;
        this.ban = ban;
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

    public void setType(String type) {
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

    public String getType() {
        return type;
    }

    public Integer getBan() {
        return ban;
    }
}
