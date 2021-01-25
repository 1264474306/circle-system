package com.iswust.dao;

import com.iswust.model.Post;
import org.apache.ibatis.annotations.Select;

public interface IPostDao {

    void postSave(Post post);
}
