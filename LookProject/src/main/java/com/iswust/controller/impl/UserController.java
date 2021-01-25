package com.iswust.controller.impl;


import com.iswust.controller.IUserController;
import com.iswust.dao.IUserDao;
import com.iswust.model.Post;
import com.iswust.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController implements IUserController {
    @Autowired
    IUserDao user;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello,World!";
    }

    @RequestMapping("/test")
    public List<User> ftindUser() {
        return user.findAll();
    }

    @RequestMapping("/enter")
    public User findUser(@RequestBody User user) {
        return user;
    }

}
