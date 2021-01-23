package com.iswust.model;

public class theme {
    private Integer id;
    private String topic;
    private Integer contentNum;
    private Integer browseNum;
    private String introduce;

    @Override
    public String toString() {
        return "theme{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", contentNum=" + contentNum +
                ", browseNum=" + browseNum +
                ", introduce='" + introduce + '\'' +
                '}';
    }

    public theme(Integer id, String topic, Integer contentNum, Integer browseNum, String introduce) {
        this.id = id;
        this.topic = topic;
        this.contentNum = contentNum;
        this.browseNum = browseNum;
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

    public Integer getContentNum() {
        return contentNum;
    }

    public void setContentNum(Integer contentNum) {
        this.contentNum = contentNum;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
