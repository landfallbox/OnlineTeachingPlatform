package com.example.onlineteachingplatform.controller;

import com.example.onlineteachingplatform.entity.User;
import com.example.onlineteachingplatform.service.UserService;
import com.example.onlineteachingplatform.utils.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        User newUser = userService.register(user.getUsername(), user.getPassword());

        if(newUser != null) {
            return Result.success("success", newUser);
        } else {
            return Result.error(1, "用户名已存在");
        }
    }

    @GetMapping("/login")
    public Result<User> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.login(username, password);

        if(user != null) {
            return Result.success("success", user);
        } else {
            return Result.error(2, "用户名或密码错误");
        }
    }
}
