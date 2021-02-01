package com.iswust.model.vo;

public class PostPathId {
    String pic_path;
    Integer id;

    public PostPathId() {
    }

    public PostPathId(String pic_path, Integer id) {
        this.pic_path = pic_path;
        this.id = id;
    }

    @Override
    public String toString() {
        return "PostPathId{" +
                "pic_path='" + pic_path + '\'' +
                ", id=" + id +
                '}';
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
