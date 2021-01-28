package com.iswust.dao;

import com.iswust.model.Post;

import com.iswust.model.PostStateId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostDao {

//    void postSave(Post post);
//
//    List<Post> postRecommend();
//
//    List<Post> postLast();
//
//    void postDelete(Post post);

    List<Post> postState(PostStateId postStateId);
}
