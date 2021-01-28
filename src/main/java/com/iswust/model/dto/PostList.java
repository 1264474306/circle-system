package com.iswust.model.dto;

import com.iswust.model.Post;

import java.util.List;

public class PostList {//用于接受List<Post>,因为object不能直接接受List<Post>
    List<Post> post;

    public PostList() {
    }

    @Override
    public String toString() {
        return "PostList{" +
                "post=" + post +
                '}';
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
}
