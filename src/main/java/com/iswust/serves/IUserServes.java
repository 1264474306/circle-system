package com.iswust.serves;

import com.iswust.model.User;


public interface IUserServes {
    void userBan(User user);

    Integer userFindById(Integer id);

    User userGet(User user);


}
