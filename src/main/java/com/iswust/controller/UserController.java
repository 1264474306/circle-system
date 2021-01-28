package com.iswust.controller;



import com.iswust.dao.IUserDao;
import com.iswust.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    IUserDao user;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello,World!";
    }

    @RequestMapping("/test")
    public List<User> findUser() {
        return user.findAll();
    }
//    @RequestMapping("/test")
//    public String findUser() {
//        return "hao";
//    }

//    @RequestMapping("/enter")
//    public User findUser(@RequestBody User user) {
//        return user;
//    }

}
