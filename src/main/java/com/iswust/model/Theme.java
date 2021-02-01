package com.iswust.model;

import java.io.Serializable;


public class Theme implements Serializable {
    private Integer id;//话题id,由后端生成返回
    private String topic;//话题的标题
    private Integer content_num;//内容数
    private Integer browse_num;//浏览数
    private String introduce;//介绍内容

    public Theme() {
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", content_num=" + content_num +
                ", browse_num=" + browse_num +
                ", introduce='" + introduce +
                '}';
    }

    public Theme(Integer id, String topic, Integer content_num, Integer browse_num, String introduce) {
        this.id = id;
        this.topic = topic;
        this.content_num = content_num;
        this.browse_num = browse_num;
        this.introduce = introduce;
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

}
