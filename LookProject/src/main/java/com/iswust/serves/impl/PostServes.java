package com.iswust.serves.impl;

import com.iswust.dao.IPostDao;
import com.iswust.model.Post;
import com.iswust.serves.IPostServes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostServes implements IPostServes {
    @Autowired
    IPostDao postDao;

    @Override
    public List<Post> postRecommend() {
        return null;
    }

    @Override
    public List<Post> postLast() {
        return null;
    }

    @Override
    public void postSave(Post post) {
        postDao.postSave(post);
    }

    @Override
    public void postDelete(Post post) {

    }
}
