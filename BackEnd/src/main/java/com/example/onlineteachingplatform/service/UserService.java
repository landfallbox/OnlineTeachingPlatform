package com.example.onlineteachingplatform.service;

import com.example.onlineteachingplatform.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     * 注册
     * @param user 用户
     */
    User register(User user);

    /**
     * 登录
     * @param id 用户账号
     * @param password 密码
     */
    User login(Integer id, String password);
}
