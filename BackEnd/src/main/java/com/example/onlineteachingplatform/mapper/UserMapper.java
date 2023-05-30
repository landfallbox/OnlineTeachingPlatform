package com.example.onlineteachingplatform.mapper;

import com.example.onlineteachingplatform.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO users (username, password) VALUES (#{username}, #{password})")
    int insert(String username, String password);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User selectByUsername(String username);
}
