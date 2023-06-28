package com.example.onlineteachingplatform.entity.dto;

import lombok.Data;

/**
 * @author : Samoye
 * @date : 2023/6/25 025 16:17
 */

@Data
public class RegisterDTO {
    // 用户名
    private String username;
    // 密码
    private String password;
    // 角色 0表示管理员 1表示学生 2表示教师
    private int role;
}
