package com.iswust.controller;


import com.iswust.model.Post;
import com.iswust.model.User;
import com.iswust.model.dto.PostList;
import com.iswust.model.dto.PostUserLikeTopic;
import com.iswust.serves.IPostServes;
import com.iswust.serves.IRedisServes;
import com.iswust.serves.IThemeServes;
import com.iswust.serves.IUserServes;
import com.iswust.util.ResponseModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    IUserServes userServes;
    @Autowired
    IThemeServes themeServes;
    @Autowired
    IRedisServes redisServes;

    //    public List<Post> postRecommend() {
//        return postServes.postRecommend();
//    }
//
    /**
     * 储存图片
     */
    @RequestMapping("/image")
//    public ResponseEntity<ResponseModel> postImage(Integer id, MultipartFile[] multipartFiles) throws IOException {
    public ResponseEntity<ResponseModel> postImage(@RequestParam("multipartFiles[]") List<MultipartFile> multipartFiles,
                                                   @RequestParam("id") Integer id,
                                                   HttpServletRequest request) throws IOException {
        Integer resultId = postServes.postFindById(id);//检查该id是否在数据库中
//        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
//                .getFiles("multipartFiles");
//        System.out.println(id);
//        System.out.println(multipartFiles.size());
//        System.out.println(files);


        if (resultId == null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("贴子id为空或错误");
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
        responseModel.setMessage("操作成功");
        responseModel.setData(pic_path);//返回地址
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }


    @RequestMapping("/test")
    public String postTest() {
        return "test疑似含有敏感词";
    }


    /**
     * 获取最新贴子
     * @param post
     * @param request
     * @return
     */
    @RequestMapping("/latest")
    public ResponseEntity<ResponseModel> postLatest(@RequestBody Post post, HttpServletRequest request){
        List<PostUserLikeTopic> posts = postServes.postLatest(post);
//        System.out.println(posts.get(1));

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功");
        responseModel.setData(posts);
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    @RequestMapping("/recommend")
    public ResponseEntity<ResponseModel> postRecommend(@RequestBody Post post, HttpServletRequest request){
        if(post.getUid() == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("用户uid为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }
        if(userServes.userFindById(post.getUid()) == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("当前id贴子不存在");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        List<PostUserLikeTopic> posts = postServes.postRecommend(post);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功");
        responseModel.setData(posts);
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    @RequestMapping("/hot")
    public ResponseEntity<ResponseModel> postHot(@RequestBody Post post, HttpServletRequest request){
        if(post.getTheme_id() == null || post.getUid() == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("话题theme_id或用户uid为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }
        if(themeServes.themeGet(post.getTheme_id()) == null || userServes.userFindById(post.getUid()) == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("当前话题theme_id或用户uid不存在");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        List<PostUserLikeTopic> posts = postServes.postHot(post);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功");
        responseModel.setData(posts);
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }


    /**
     * 获取某个贴子
     * @param post
     * @param request
     * @return
     */
    @RequestMapping("/query")
    public ResponseEntity<ResponseModel> postGet(@RequestBody Post post, HttpServletRequest request) {
        if (post.getId() == null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("贴子id为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        if(postServes.postFindById(post.getId()) == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("当前id贴子不存在");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        PostUserLikeTopic postUserLikeTopic = postServes.postGet(post);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功");
        responseModel.setData(postUserLikeTopic);
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
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
        post.setState((String) request.getAttribute("state"));

        if (post.getUid() == null || post.getTheme_id() == null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("用户uid或话题Theme_id为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }
        if(userServes.userFindById(post.getUid())== null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("当前用户不存在");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }
        if(themeServes.themeGet(post.getTheme_id()) == null){
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("当前话题不存在");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        postServes.postCommit(post);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功");
        responseModel.setData(post);
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    /**
     * 删除
     * @param post
     * @return
     */
    @RequestMapping("/delete")
    public ResponseEntity<ResponseModel> postDelete(@RequestBody Post post, HttpServletRequest request) {
        ResponseModel responseModel = new ResponseModel();

        if (post.getId() == null) {
            responseModel.setCode(0);
            responseModel.setMessage("贴子id为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        if(postServes.postFindById(post.getId()) == null){
            responseModel.setCode(0);
            responseModel.setMessage("当前id贴子不存在");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        String pic_paths = postServes.postFindPathById(post.getId());
        if(pic_paths != null){
            String[] paths = pic_paths.split(";");
            for(String path : paths){
                String filename = StringUtils.substringAfterLast(path,"/");
                new File(uploadFolder + uploadImage + "/" + filename).delete();
            }
        }

        postServes.postDelete(post);
        redisServes.redisDelete(post.getId());

        responseModel.setCode(1);
        responseModel.setMessage("操作成功");
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    /**
     * 申请状态
     * @param user
     * @param request
     * @return
     */
    @RequestMapping("/state")//返回表单的状态
    public ResponseEntity<ResponseModel> postState(@RequestBody User user, HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        String id = null;
//
//        if (cookies == null) {
//            ResponseModel responseModel = new ResponseModel();
//            responseModel.setCode(0);
//            responseModel.setMessage("cookie为空(0为操作错误 1则正确)");
//            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
//        }
//
//        for (Cookie cookie : cookies) {
//            if (cookie.getName() == "id") {
//                id = cookie.getValue();
//            }
//
//        }
        Integer id = user.getId();

        if (id == null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("用户id数据为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        List<Post> posts = postServes.postState(id);//调用postServes
//        List<Post> posts = postServes.postState(1);//测试用代码
        PostList postList = new PostList();
        postList.setPost(posts);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功");
        responseModel.setData(postList);

        //ResponseEntity和ResponseBody类似都是返回信息,只是entity多了个状态参数
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }
}
