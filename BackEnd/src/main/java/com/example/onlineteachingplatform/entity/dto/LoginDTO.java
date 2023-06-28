package com.example.onlineteachingplatform.entity.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author : Samoye
 * @date : 2023/6/25 025 15:59
 */

@Data
public class LoginDTO {
    // 用户 id
    @JsonAlias({"id", "userId"})
    private Integer id;
    // 用户密码
    @JsonAlias({"password", "userPassword", "pwd"})
    private String password;
}
