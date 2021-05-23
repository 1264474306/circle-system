package com.iswust.serves.impl;

import com.iswust.dao.IUserDao;
import com.iswust.model.User;
import com.iswust.serves.IUserServes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServes implements IUserServes {
    @Autowired
    IUserDao userDao;

    @Override
    public void userBan(User user) {
        userDao.userBan(user);
    }

    @Override
    public Integer userFindById(Integer id) {
        return userDao.userFindById(id);
    }

    @Override
    public User userGet(User user) {
        return userDao.userGet(user.getId());
    }
}
