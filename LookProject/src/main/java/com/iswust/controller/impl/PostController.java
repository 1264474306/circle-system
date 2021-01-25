package com.iswust.controller.impl;

import com.iswust.controller.IPostController;
import com.iswust.model.Post;
import com.iswust.serves.IPostServes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostController implements IPostController {

    @Autowired
    IPostServes postServes;

    @Override
    public List<Post> postRecommend() {
        return postServes.postRecommend();
    }

    @Override
    public List<Post> postLast() {
        return postServes.postLast();
    }

    @Override
    public void postSave(Post post) {
        postServes.postSave(post);
    }

    @Override
    public void postDelete(Post post) {
        postServes.postDelete(post);
    }
}
