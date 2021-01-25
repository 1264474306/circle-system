package com.iswust.serves;

import com.iswust.model.Post;

import java.util.List;

public interface IPostServes {
    List<Post> postRecommend();

    List<Post> postLast();

    void postSave(Post post);

    void postDelete(Post post);
}
