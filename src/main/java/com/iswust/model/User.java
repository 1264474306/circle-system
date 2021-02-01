package com.iswust.model;

import java.io.Serializable;



public class User implements Serializable {
    private Integer id;//用户id,由后端生成返回
    private String account;//用户账号,暂时用不上
    private String password;//用户密码,暂时用不上
    private String photo;//用户照片路径,由后端生成返回
    private String name;//用户名称
    private Integer type;//用户类型(管理员,普通用户
    private Integer ban;//是否禁止发帖 禁止1 正常0


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
                '}';
    }

    public User(Integer id, String account, String password, String photo, String name, Integer type, Integer ban) {
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