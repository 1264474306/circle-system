package com.iswust.model;

import java.sql.Timestamp;

public class post {
    private Integer id;
    private Timestamp timestamp;
    private String topic;
    private String  content;
    private String photo;
    private Integer pointNum;
    private String type;

    @Override
    public String toString() {
        return "post{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", photo='" + photo + '\'' +
                ", pointNum=" + pointNum +
                ", type='" + type + '\'' +
                '}';
    }

    public post(Integer id, Timestamp timestamp, String topic, String content, String photo, Integer pointNum, String type) {
        this.id = id;
        this.timestamp = timestamp;
        this.topic = topic;
        this.content = content;
        this.photo = photo;
        this.pointNum = pointNum;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getPointNum() {
        return pointNum;
    }

    public void setPointNum(Integer pointNum) {
        this.pointNum = pointNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
