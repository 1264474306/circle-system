package com.iswust.dao;

import com.iswust.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {


    List<User> findAll();

    void userBan(User user);

    Integer userFindById(Integer id);

    String userFindName(Integer launch_uid);

    User userFindNamePhoto(Integer uid);

    User userGet(Integer id);
}
