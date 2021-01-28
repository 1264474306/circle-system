package com.iswust.serves.impl;

import com.iswust.dao.IPostDao;
import com.iswust.model.Post;
import com.iswust.model.PostStateId;
import com.iswust.serves.IPostServes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServes implements IPostServes {
    @Autowired
    IPostDao postDao;

//    @Override
//    public List<Post> postRecommend() {
//        return postDao.postRecommend();
//    }
//
//    @Override
//    public List<Post> postLast() {
//        return postDao.postLast();
//    }
//
//    @Override
//    public void postSave(Post post) {
//        postDao.postSave(post);
//    }
//
//    @Override
//    public void postDelete(Post post) {
//        postDao.postDelete(post);
//    }

    @Override
    public List<Post> postState(Integer id) {
        String state = "normal";
        PostStateId postStateId = new PostStateId(state,id);
        return postDao.postState(postStateId);
    }
}
