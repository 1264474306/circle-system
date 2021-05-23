package com.iswust.model;


import java.io.Serializable;


public class Message implements Serializable {
    private Integer id;
    private String type;
    private Integer stage;
    private Integer judge_id;
    private Integer post_id;
    private Integer launch_uid;
    private Integer receive_uid;

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", stage=" + stage +
                ", judge_id=" + judge_id +
                ", post_id=" + post_id +
                ", launch_uid=" + launch_uid +
                ", receive_uid=" + receive_uid +
                '}';
    }

    public Message(Integer id, String type, Integer stage, Integer judge_id, Integer post_id, Integer launch_uid, Integer receive_uid) {
        this.id = id;
        this.type = type;
        this.stage = stage;
        this.judge_id = judge_id;
        this.post_id = post_id;
        this.launch_uid = launch_uid;
        this.receive_uid = receive_uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public Integer getJudge_id() {
        return judge_id;
    }

    public void setJudge_id(Integer judge_id) {
        this.judge_id = judge_id;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public Integer getLaunch_uid() {
        return launch_uid;
    }

    public void setLaunch_uid(Integer launch_uid) {
        this.launch_uid = launch_uid;
    }

    public Integer getReceive_uid() {
        return receive_uid;
    }

    public void setReceive_uid(Integer receive_uid) {
        this.receive_uid = receive_uid;
    }

}
