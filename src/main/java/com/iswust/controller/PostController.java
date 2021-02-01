package com.iswust.controller;


import com.iswust.model.Post;
import com.iswust.model.User;
import com.iswust.model.dto.PostList;
import com.iswust.serves.IPostServes;
import com.iswust.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;


@RequestMapping("/post")
@RestController
public class PostController {

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.uploadImage}")
    private String uploadImage;
    @Value("${file.prefix}")
    private String prefix;

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

    /**
     * 储存图片
     */
    @RequestMapping("/image")
    public ResponseEntity<ResponseModel> postImage(Integer id, MultipartFile[] multipartFiles) throws IOException {
        Integer resultId = postServes.postFindById(id);//检查该id是否在数据库中
        if (resultId == null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("贴子id为空或错误(0为操作错误 1则正确)");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        StringBuffer pic_path = new StringBuffer();
        for (MultipartFile multipartFile : multipartFiles) {
            String originalFilename = multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File( uploadFolder + uploadImage + originalFilename));//写入图片
            pic_path.append(prefix + staticAccessPath + originalFilename + ";");//保存地址
        }

        postServes.postSavePath(pic_path, id);//保存地址到数据库

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功(0为操作错误 1则正确)");
        responseModel.setData(pic_path);//返回地址
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }


    @RequestMapping("/test")
    public String postTest() {
        return "test疑似含有敏感词";
    }

    /**
     * 提交
     */
    @RequestMapping("/commit")
    public ResponseEntity<ResponseModel> postCommit(HttpServletRequest request) {
        Post post = new Post();//从request中取出数据到post中    转发不能两次@requestbody
        post.setUid((Integer) request.getAttribute("uid"));
        post.setTheme_id((Integer) request.getAttribute("theme_id"));
        post.setContent((String) request.getAttribute("content"));
        post.setTopic((String) request.getAttribute("topic"));

        if (post.getUid() == null || post.getTheme_id() == null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("用户uid或话题Theme_id为空(0为操作错误 1则正确)");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        postServes.postCommit(post);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功(0为操作错误 1则正确)");
        responseModel.setData(post);
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }
//
//    public void postDelete(Post post) {
//        postServes.postDelete(post);
//    }

    /**
     * 点赞
     */
    @RequestMapping("/like")
    public ResponseEntity<ResponseModel> postLike(@RequestBody Post post, HttpServletRequest request) {
        if (post.getId() == null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("贴子id为空(0为操作错误 1则正确)");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        postServes.postLike(post);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功(0为操作错误 1则正确)");
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    /**
     * 转发数+1
     * @param post
     * @param request
     * @return
     */
    @RequestMapping("forward")
    public ResponseEntity<ResponseModel> postforward(@RequestBody Post post, HttpServletRequest request){
        if(post.getId() == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("贴子id为空(0为操作错误 1则正确)");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        postServes.postForward(post);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功(0为操作错误 1则正确)");
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    /**
     * 状态
     */
    @RequestMapping("/state")//返回表单的状态
    public ResponseEntity<ResponseModel> postState(@RequestBody User user, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String id = null;

        if (cookies == null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("cookie为空(0为操作错误 1则正确)");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName() == "id") {
                id = cookie.getValue();
            }

        }

        if (id == null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("用户id数据为空(0为操作错误 1则正确)");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        List<Post> posts = postServes.postState(Integer.valueOf(id));//调用postServes
//        List<Post> posts = postServes.postState(1);//测试用代码
        PostList postList = new PostList();
        postList.setPost(posts);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功(0为操作错误 1则正确)");
        responseModel.setData(postList);

        //ResponseEntity和ResponseBody类似都是返回信息,只是entity多了个状态参数
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }
}
