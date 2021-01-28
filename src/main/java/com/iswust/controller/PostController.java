package com.iswust.controller;


import com.iswust.model.Post;
import com.iswust.model.User;
import com.iswust.model.dto.PostList;
import com.iswust.serves.IPostServes;
import com.iswust.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RequestMapping("/post")
@RestController
public class PostController  {

    @Autowired
    IPostServes postServes;

//    public List<Post> postRecommend() {
//        return postServes.postRecommend();
//    }
//
//    public List<Post> postLast() {
//        return postServes.postLast();
//    }
//
//    public void postSave(Post post) {
//        postServes.postSave(post);
//    }
//
//    public void postDelete(Post post) {
//        postServes.postDelete(post);
//    }

    @RequestMapping("/state")//返回表单的状态
    public ResponseEntity<ResponseModel> postState(@RequestBody User user, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String id = null;

        if(cookies == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("用户id数据为空");
            return new ResponseEntity<ResponseModel>(responseModel,HttpStatus.OK);
        }

        for(Cookie cookie : cookies){
            if(cookie.getName()=="id"){
                id = cookie.getValue();
            }

        }

        if(id == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setMessage("用户id数据为空");
            return new ResponseEntity<ResponseModel>(responseModel,HttpStatus.OK);
        }

        List<Post> posts = postServes.postState(Integer.valueOf(id));//调用postServes
//        List<Post> posts = postServes.postState(1);//测试用代码
        PostList postList = new PostList();
        postList.setPost(posts);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(postList);

        //ResponseEntity和ResponseBody类似都是返回信息,只是entity多了个状态参数
        return new ResponseEntity<ResponseModel>(responseModel,HttpStatus.OK);
    }
}
