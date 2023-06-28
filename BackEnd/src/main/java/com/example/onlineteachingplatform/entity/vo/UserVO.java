package com.example.onlineteachingplatform.entity.vo;

import lombok.Data;

/**
 * @author Samoye
 */
@Data
public class UserVO {
    // 用户 id
    private Integer id;
    // 用户名
    private String username;
    // 角色 0表示管理员 1表示学生 2表示教师
    private int role;
}
