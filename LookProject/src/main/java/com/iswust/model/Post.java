package com.iswust.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;

public class Post implements Serializable {
    private Integer id;
    private Timestamp timestamp;
    private String topic;
    private String  content;
    private String pic;
    private Integer point_num;
    private String type;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", pic='" + pic + '\'' +
                ", point_num=" + point_num +
                ", type='" + type + '\'' +
                '}';
    }

    public Post(Integer id, Timestamp timestamp, String topic, String content, String pic, Integer point_num, String type) {
        this.id = id;
        this.timestamp = timestamp;
        this.topic = topic;
        this.content = content;
        this.pic = pic;
        this.point_num = point_num;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getPoint_num() {
        return point_num;
    }

    public void setPoint_num(Integer point_num) {
        this.point_num = point_num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
