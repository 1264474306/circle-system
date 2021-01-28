package com.iswust.model.dto;

import com.iswust.model.Post;
import com.iswust.model.User;

import java.sql.Timestamp;

public class PostUser extends Post{
    private User user;

    @Override
    public String toString() {
        return "PostUser{" +
                "user=" + user +
                "} " + super.toString();
    }

    public PostUser(Integer id, Integer uid, Integer theme_id, Timestamp timestamp, String topic, String content, String pic, Integer point_num, String state, User user) {
        super(id, uid, theme_id, timestamp, topic, content, pic, point_num, state);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
