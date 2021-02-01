package com.iswust.serves;

import com.iswust.model.Post;

import java.util.List;


public interface IPostServes {


    List<Post> postState(Integer id);

    void postLike(Post post);

    void postCommit(Post post);

    Integer postFindById(Integer id);

    void postSavePath(StringBuffer pic_path, Integer id);

    void postForward(Post post);
}
