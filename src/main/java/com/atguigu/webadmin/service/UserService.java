package com.atguigu.webadmin.service;

import com.atguigu.webadmin.bean.User;
import com.atguigu.webadmin.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getUserById(Integer id){
       return userDao.getUserById(id);
    }
}
