package com.example.onlineteachingplatform.mapper;

import com.example.onlineteachingplatform.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO users (username, password, role) VALUES (#{username}, #{password}, #{role})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(User user);

    @Select("SELECT * FROM users WHERE id = #{id}")
    User selectById(Integer id);
}
