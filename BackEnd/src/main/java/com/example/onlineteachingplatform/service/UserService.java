package com.example.onlineteachingplatform.service;

import com.example.onlineteachingplatform.entity.dto.LoginDTO;
import com.example.onlineteachingplatform.entity.dto.RegisterDTO;
import com.example.onlineteachingplatform.entity.vo.UserVO;
import org.springframework.stereotype.Service;

/**
 * @author Samoye
 */
@Service
public interface UserService {
    /**
     * 注册
     * @param user 注册信息
     */
    LoginDTO register(RegisterDTO user);

    /**
     * 登录
     * @param loginDto 登录信息
     */
    UserVO login(LoginDTO loginDto);
}
