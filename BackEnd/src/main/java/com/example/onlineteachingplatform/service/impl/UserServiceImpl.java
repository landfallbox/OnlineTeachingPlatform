package com.example.onlineteachingplatform.service.impl;

import com.example.onlineteachingplatform.entity.dto.LoginDTO;
import com.example.onlineteachingplatform.entity.dto.RegisterDTO;
import com.example.onlineteachingplatform.entity.po.UserPO;
import com.example.onlineteachingplatform.entity.vo.UserVO;
import com.example.onlineteachingplatform.dao.UserDao;
import com.example.onlineteachingplatform.mapper.UserMapper;
import com.example.onlineteachingplatform.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Samoye
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public LoginDTO register(RegisterDTO registerDto) {
        // 数据类型转换 分层
        UserPO userPo = UserMapper.INSTANCE.toUserPo(registerDto);
        // 插入
        int rowChanged = userDao.insert(userPo);
        // 插入失败
        if (rowChanged != 1) {
            return null;
        }
        // 插入成功
        return UserMapper.INSTANCE.toLoginDto(userPo);
    }

    @Override
    public UserVO login(LoginDTO loginDto) {
        UserPO userPo = UserMapper.INSTANCE.toUserPo(loginDto);
        return userDao.selectByIdAndPwd(userPo);
    }
}
