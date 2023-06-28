package com.example.onlineteachingplatform.controller;

import com.example.onlineteachingplatform.entity.dto.LoginDTO;
import com.example.onlineteachingplatform.entity.dto.RegisterDTO;
import com.example.onlineteachingplatform.entity.vo.UserVO;
import com.example.onlineteachingplatform.service.UserService;
import com.example.onlineteachingplatform.utils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author Samoye
 * @date 2023/6/25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Result<UserVO> register(@RequestBody RegisterDTO user) {
        // 注册
        LoginDTO newUser = userService.register(user);

        // 注册成功
        if(newUser != null) {
            // 注册成功后自动登录
            UserVO userVO = userService.login(newUser);

            if(userVO != null) {
                return Result.success(0, "register and autologin successfully", userVO);
            }
            else {
                return Result.error(3, "register successfully but fail to autologin ");
            }
        }
        // 注册失败
        else {
            return Result.error(1, "fail to register");
        }
    }

    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody LoginDTO loginDto) {
        // 登录
        UserVO user = userService.login(loginDto);
        // 登录成功
        if(user != null) {
            return Result.success(2, "login success", user);
        }
        // 登录失败
        else {
            return Result.error(3, "login fail");
        }
    }
}
