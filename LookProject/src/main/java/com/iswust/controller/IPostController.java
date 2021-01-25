package com.iswust.controller;

import com.iswust.model.Post;

import java.util.List;

public interface IPostController {
    public List<Post> postRecommend();

    public List<Post> postLast();

    public void postSave(Post post);

    public void postDelete(Post post);

}