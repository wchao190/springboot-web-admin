package com.atguigu.webadmin.dao;


import com.atguigu.webadmin.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User getUserById(Integer id);
}
