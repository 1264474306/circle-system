package com.iswust.dao;

import com.iswust.model.Post;

import com.iswust.model.vo.PostPathId;
import com.iswust.model.vo.PostStateId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostDao {



    List<Post> postState(PostStateId postStateId);

    void postLike(Integer id);

    void postCommit(Post post);

    Integer postFindById(Integer id);

    void postSavePath(PostPathId postPathId);

    void postForward(Integer id);
}
