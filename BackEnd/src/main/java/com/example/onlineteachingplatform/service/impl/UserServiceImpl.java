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
    public User register(User user) {
        int result = userMapper.insert(user);

        if(result == 1) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User login(Integer id, String password) {
        User user = userMapper.selectById(id);

        if(user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}
