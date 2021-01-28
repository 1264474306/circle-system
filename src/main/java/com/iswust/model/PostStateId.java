package com.iswust.model;

public class PostStateId {
    private String  state;
    private Integer id;

    public PostStateId(String state, Integer id) {
        this.state = state;
        this.id = id;
    }

    public PostStateId() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
