package com.iswust.controller;



import com.iswust.model.User;
import com.iswust.serves.IUserServes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    IUserServes userServes;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello,World!";
    }

//    @RequestMapping("/test")
//    public List<User> findUser() {
//        return user.findAll();
//    }

    @RequestMapping("/ban")
    public Integer userBan(@RequestBody User user) {
        return userServes.userBan(user);
    }


}
