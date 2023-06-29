package com.example.onlineteachingplatform.dao;

import com.example.onlineteachingplatform.entity.po.UserPO;
import com.example.onlineteachingplatform.entity.vo.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @author Samoye
 */
@Mapper
public interface UserDao {
    @Insert("""
            INSERT INTO users (username, password, role)
            VALUES (#{userPo.username}, #{password}, #{role})
            """)
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(UserPO userPo);

    @Select("""
            SELECT id, username, role
            FROM users
            WHERE id = #{id} AND password = #{password}
            """)
    UserVO selectByIdAndPwd(UserPO userPo);

    /**
     * 根据用户 id 查询用户名
     */
    @Select("""
            SELECT username
            FROM users
            WHERE id = #{id}
            """)
    String selectNameById(Integer id);
}
