package com.iswust.serves.impl;

import com.iswust.dao.IPostDao;
import com.iswust.model.Post;
import com.iswust.model.vo.PostPathId;
import com.iswust.model.vo.PostStateId;
import com.iswust.serves.IPostServes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PostServes implements IPostServes {
    @Autowired
    IPostDao postDao;



    @Override
    public List<Post> postState(Integer id) {
        String state = "normal";
        PostStateId postStateId = new PostStateId(state,id);

        return postDao.postState(postStateId);
    }

    @Override
    public void postLike(Post post) {
        postDao.postLike(post.getId());
    }

    @Override
    public void postCommit(Post post) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        post.setTimestamp(timestamp);
        post.setState("normal");
        post.setPoint_num(0);
        post.setForward_num(0);

        postDao.postCommit(post);
    }

    @Override
    public Integer postFindById(Integer id) {
        return postDao.postFindById(id);
    }

    @Override
    public void postSavePath(StringBuffer pic_path, Integer id) {
        PostPathId postPathId = new PostPathId();
        postPathId.setId(id);
        postPathId.setPic_path(new String(pic_path));

        postDao.postSavePath(postPathId);
    }

    @Override
    public void postForward(Post post) {
        postDao.postForward(post.getId());
    }
}
