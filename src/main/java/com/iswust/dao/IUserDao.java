package com.iswust.dao;

import com.iswust.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {


      List<User> findAll();

    Integer userBan(User user);


//    User findUser(User user);

}
