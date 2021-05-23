package com.iswust.model.dto;

import com.iswust.model.Post;
import com.iswust.model.User;

import java.io.Serializable;

public class PostUserLikeTopic implements Serializable {
    private Post post;
    private User user;
    private Integer like;
    private String theme_topic;

    @Override
    public String toString() {
        return "PostUserLike{" +
                "post=" + post +
                ", user=" + user +
                ", like=" + like +
                ", theme_topic='" + theme_topic + '\'' +
                '}';
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getTheme_topic() {
        return theme_topic;
    }

    public void setTheme_topic(String theme_topic) {
        this.theme_topic = theme_topic;
    }

    public PostUserLikeTopic() {
    }

    public PostUserLikeTopic(Post post, User user, Integer like, String theme_topic) {
        this.post = post;
        this.user = user;
        this.like = like;
        this.theme_topic = theme_topic;
    }
}
