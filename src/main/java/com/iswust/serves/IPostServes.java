package com.iswust.serves;

import com.iswust.model.Post;
import com.iswust.model.dto.PostUserLikeTopic;

import java.util.List;


public interface IPostServes {


    List<Post> postState(Integer id);

    void postLike(Post post);

    void postCommit(Post post);

    Integer postFindById(Integer id);

    void postSavePath(StringBuffer pic_path, Integer id);

    void postDelete(Post post);

    void postUnlike(Post post);

    PostUserLikeTopic postGet(Post post);

    String postFindPathById(Integer id);

    List<PostUserLikeTopic> postLatest(Post post);

    List<PostUserLikeTopic> postRecommend(Post post);

    List<PostUserLikeTopic> postHot(Post post);
}
