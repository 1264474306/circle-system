package com.iswust.serves;

import com.iswust.model.Message;
import com.iswust.model.dto.MessageNameTopic;

import java.util.List;


public interface IMessageServes {
    Integer messageLike(Message message);

    Integer messageUnLike(Message message);

    Integer messageForward(Message message);

    List<MessageNameTopic> messageGet(Message message);
}
