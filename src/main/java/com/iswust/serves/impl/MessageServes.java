package com.iswust.serves.impl;


import com.iswust.dao.IMessageDao;
import com.iswust.dao.IPostDao;
import com.iswust.dao.IUserDao;
import com.iswust.model.Message;
import com.iswust.model.dto.MessageNameTopic;
import com.iswust.serves.IMessageServes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MessageServes implements IMessageServes {
    @Autowired
    IPostDao postDao;
    @Autowired
    IMessageDao messageDao;
    @Autowired
    IUserDao userDao;

    @Override
    public Integer messageLike(Message message) {
        postDao.postLike(message.getPost_id());

        message.setStage(0);
        message.setType("like");
        messageDao.messageAdd(message);

        return postDao.postGet(message.getPost_id()).getPoint_num();
    }

    @Override
    public Integer messageUnLike(Message message) {
        postDao.postUnlike(message.getPost_id());
        return postDao.postGet(message.getPost_id()).getPoint_num();
    }

    @Override
    public Integer messageForward(Message message) {
        postDao.postForward(message.getPost_id());

        message.setStage(0);
        message.setType("forward");
        messageDao.messageAdd(message);

        return postDao.postGet(message.getPost_id()).getForward_num();
    }

    @Override
    public List<MessageNameTopic> messageGet(Message message) {
        List<MessageNameTopic> messageNameTopics = new ArrayList<>();

        if(message.getStage() == 1){
            List<Message> messages = messageDao.messageManagerGet(message.getReceive_uid());
            for(Message message1 : messages){
                String topic = postDao.postFindTopic(message1.getPost_id());
                String name = userDao.userFindName(message1.getLaunch_uid());
                messageNameTopics.add(new MessageNameTopic(message1,name,topic));
            }
        }
        else{
            List<Message> messages = messageDao.messageUserGet(message.getReceive_uid());
            for(Message message1 : messages){
                String topic = postDao.postFindTopic(message1.getPost_id());
                String name = userDao.userFindName(message1.getLaunch_uid());
                messageNameTopics.add(new MessageNameTopic(message1,name,topic));
            }
        }
        return messageNameTopics;
    }
}
