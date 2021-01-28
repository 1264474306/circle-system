package com.iswust.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;

public class Post implements Serializable {
    private Integer id;
    private Integer uid;
    private Integer theme_id;
    private Timestamp timestamp;
    private String topic;
    private String  content;
    private String pic_path;
    private Integer point_num;
    private String state;

    public Post() {
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", uid=" + uid +
                ", theme_id=" + theme_id +
                ", timestamp=" + timestamp +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", pic_path='" + pic_path + '\'' +
                ", point_num=" + point_num +
                ", state='" + state + '\'' +
                '}';
    }

    public Post(Integer id, Integer uid, Integer theme_id, Timestamp timestamp, String topic, String content, String pic_path, Integer point_num, String state) {
        this.id = id;
        this.uid = uid;
        this.theme_id = theme_id;
        this.timestamp = timestamp;
        this.topic = topic;
        this.content = content;
        this.pic_path = pic_path;
        this.point_num = point_num;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(Integer theme_id) {
        this.theme_id = theme_id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic_path;
    }

    public void setPic(String pic_path) {
        this.pic_path = pic_path;
    }

    public Integer getPoint_num() {
        return point_num;
    }

    public void setPoint_num(Integer point_num) {
        this.point_num = point_num;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
