package com.iswust.controller;


import com.iswust.model.User;
import com.iswust.serves.IUserServes;
import com.iswust.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    IUserServes userServes;

    //    @RequestMapping("/hello")
    @GetMapping("/hello")
    public String hello() {
        return "Hello,World!";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    private String test(@RequestBody String[] num) {
        System.out.println(num.length);
        for (String n : num) {
            System.out.println(n);
        }
//        System.out.println(num);
        return "yes";
    }
    @RequestMapping(value = "/cookieAndForward", method = RequestMethod.GET)
    private String test1(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        response.addCookie(new Cookie("wordpresscookie", "cookie"));
        return "forward:/http://47.105.72.116/iswust_banner/pc_index.html";
    }

    /**
     * 用户禁止
     *
     * @param user
     * @return
     */
    @RequestMapping("/ban")
    public ResponseEntity<ResponseModel> userBan(@RequestBody User user) {
        if (user.getId() == null || user.getBan() == null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("用户id或是否禁止ban为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }
        if (userServes.userFindById(user.getId()) == null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("当前用户不存在");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        userServes.userBan(user);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功");
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

    @RequestMapping("/query")
    public ResponseEntity<ResponseModel> userGet(@RequestBody User user) {
        if (user.getId() == null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("用户id或是否禁止ban为空");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }
        if (userServes.userFindById(user.getId()) == null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setCode(0);
            responseModel.setMessage("当前用户不存在");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        }

        User user1 = userServes.userGet(user);

        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(1);
        responseModel.setMessage("操作成功");
        responseModel.setData(user1);
        return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
    }

}
