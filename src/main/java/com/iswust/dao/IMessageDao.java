package com.iswust.dao;

import com.iswust.model.Message;
import com.iswust.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageDao {
    void messageAdd(Message message);

    void messageDelete(Integer id);

    List<Message> messageManagerGet(Integer receive_uid);

    List<Message> messageUserGet(Integer receive_uid);

    void messageUpdateStage(Integer id);

    Integer messageFindByUidPostId(Post post);
}
