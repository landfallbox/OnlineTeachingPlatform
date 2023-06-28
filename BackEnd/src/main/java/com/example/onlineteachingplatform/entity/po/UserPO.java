package com.example.onlineteachingplatform.entity.po;

import lombok.Data;

/**
 * @author Samoye
 */
@Data
public class UserPO {
    private Integer id;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 角色 0表示管理员 1表示学生 2表示教师
    private int role;
}
