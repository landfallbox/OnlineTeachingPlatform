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
        User newUser = userService.register(user);

        if(newUser != null) {
            User loginUser = userService.login(newUser.getId(), newUser.getPassword());

            if(loginUser != null){
                return Result.success(0, "register and auto login success", loginUser);
            }
            else{
                return Result.error(3, "register success while login fail");
            }
        } else {
            return Result.error(1, "register fail");
        }
    }

    @GetMapping("/login")
    public Result<User> login(@RequestParam Integer id, @RequestParam String password) {
        User user = userService.login(id, password);

        if(user != null) {
            return Result.success(2, "login success", user);
        } else {
            return Result.error(3, "login fail");
        }
    }
}
