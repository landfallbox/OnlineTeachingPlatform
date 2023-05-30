package com.example.onlineteachingplatform.service;

import com.example.onlineteachingplatform.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     */
    User register(String username, String password);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     */
    User login(String username, String password);
}
