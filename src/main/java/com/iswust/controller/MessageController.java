package com.iswust.controller;

import com.iswust.model.Message;
import com.iswust.model.dto.MessageNameTopic;
import com.iswust.serves.*;
import com.iswust.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/message")
@RestController
public class MessageController {
    @Autowired
    IPostServes postServes;
    @Autowired
    IUserServes userServes;
    @Autowired
    IMessageServes messageServes;
    @Autowired
    IRedisServes redisServes;
    @Autowired
    IThemeServes themeServes;

    /**
     * 点赞
     */
    @RequestMapping("/like")
    public ResponseEntity<ResponseModel> messageLike(@RequestBody Message message, HttpServletRequest request) {

        if (message.getPost_id() == null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("贴子id为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        if(postServes.postFindById(message.getPost_id()) == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("当前id贴子不存在");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        Integer point_num = messageServes.messageLike(message);

        redisServes.redisLike(message.getPost_id());

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功");
        responseModel.setData(point_num);
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    @RequestMapping("/unlike")
    public ResponseEntity<ResponseModel> messageUnLike(@RequestBody Message message, HttpServletRequest request) {
        ResponseModel responseModel = new ResponseModel();

        if (message.getPost_id() == null) {
            responseModel.setCode(0);
            responseModel.setMessage("贴子id为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        if(postServes.postFindById(message.getPost_id()) == null){
            responseModel.setCode(0);
            responseModel.setMessage("当前id贴子不存在");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        Integer point_num = messageServes.messageUnLike(message);
        redisServes.redisUnLike(message.getPost_id());

        responseModel.setCode(1);
        responseModel.setMessage("操作成功");
        responseModel.setData(point_num);
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    /**
     * 转发数
     * @param
     * @param request
     * @return
     */
    @RequestMapping("/forward")
    public ResponseEntity<ResponseModel> messageForward(@RequestBody Message message, HttpServletRequest request){

        if(message.getPost_id() == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("贴子id为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        if(postServes.postFindById(message.getPost_id()) == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("当前id贴子不存在");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        Integer forward_num = messageServes.messageForward(message);
        redisServes.redisForward(message.getPost_id());

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功");
        responseModel.setData(forward_num);
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    /**
     * 获取消息
     * @param message
     * @param request
     * @return
     */
    @RequestMapping("/query")
    public ResponseEntity<ResponseModel> messageGet(@RequestBody Message message, HttpServletRequest request){
        if(message.getStage() == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("消息状态stage为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }
        if(message.getReceive_uid() == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("接受者receive_uid为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        List<MessageNameTopic> messages = messageServes.messageGet(message);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setData(messages);
        responseModel.setMessage("操作成功");
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }



}
