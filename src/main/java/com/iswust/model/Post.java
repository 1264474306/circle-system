package com.iswust.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

public class Post implements Serializable {
    private Integer id; //贴子id,由后端生成返回
    private Integer uid;//发表贴子的用户id
    private Integer theme_id;//贴子发表在的话题id
    private Timestamp timestamp;//发帖时间,后端处理 格式yyyy-MM-dd HH:mm:ss
    private String topic;//贴子题目id
    private String  content;//贴子内容
    private String pic_path;//贴子的图片路径,由后端生成返回
    private Integer point_num;//贴子的点赞数
    private String state;//贴子的状态(包括normal,check,disagree,ban),由后端生成返回
    private Integer forward_num;//转发数

    public Post() {
    }

    public Post(Integer id, Integer uid, Integer theme_id, Timestamp timestamp, String topic, String content, String pic_path, Integer point_num, String state, Integer forward_num) {
        this.id = id;
        this.uid = uid;
        this.theme_id = theme_id;
        this.timestamp = timestamp;
        this.topic = topic;
        this.content = content;
        this.pic_path = pic_path;
        this.point_num = point_num;
        this.state = state;
        this.forward_num = forward_num;
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
                ", forward_num='" + forward_num + '\'' +
                '}';
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
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

    public Integer getForward_num() {
        return forward_num;
    }

    public void setForward_num(Integer forward_num) {
        this.forward_num = forward_num;
    }
}
