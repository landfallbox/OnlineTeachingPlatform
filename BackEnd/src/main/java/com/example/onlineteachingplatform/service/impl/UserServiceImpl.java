package com.example.onlineteachingplatform.service.impl;

import com.example.onlineteachingplatform.entity.User;
import com.example.onlineteachingplatform.mapper.UserMapper;
import com.example.onlineteachingplatform.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User register(String username, String password) {
//        用户名不允许重复
        if(userMapper.selectByUsername(username) != null) {
            return null;
        }

        int result = userMapper.insert(username, password);

        if(result == 1) {
            return userMapper.selectByUsername(username);
        } else {
            return null;
        }

    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);

        if(user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}
