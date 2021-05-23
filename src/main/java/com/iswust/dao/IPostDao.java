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

    void postDelete(Integer id);

    void postUnlike(Integer id);

    Post postGet(Integer id);

    String postFindPathById(Integer id);

    void postUnForward(Integer id);

    List<Post> postFindAll(Post voPost);

    List<Post> postFindAllByThemeId(Post theme_id);

    String postFindTopic(Integer post_id);

    void postUpdateState(Post post);

//    List<Post> postRecommend(@Param("post_ids") List<Integer> post_ids);
    List<Post> postRecommend(List<Integer> post_ids);
}
