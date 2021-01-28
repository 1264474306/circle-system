package com.iswust.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;


public class Theme implements Serializable {
    private Integer id;
    private String topic;
    private Integer content_num;
    private Integer browse_num;
    private String introduce;
    private List<Post> posts;

    public Theme() {
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", content_num=" + content_num +
                ", browse_num=" + browse_num +
                ", introduce='" + introduce + '\'' +
                ", posts=" + posts +
                '}';
    }

    public Theme(Integer id, String topic, Integer content_num, Integer browse_num, String introduce, List<Post> posts) {
        this.id = id;
        this.topic = topic;
        this.content_num = content_num;
        this.browse_num = browse_num;
        this.introduce = introduce;
        this.posts = posts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getContent_num() {
        return content_num;
    }

    public void setContent_num(Integer content_num) {
        this.content_num = content_num;
    }

    public Integer getBrowse_num() {
        return browse_num;
    }

    public void setBrowse_num(Integer browse_num) {
        this.browse_num = browse_num;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
