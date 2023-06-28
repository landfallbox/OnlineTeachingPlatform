package com.example.onlineteachingplatform.dao;

import com.example.onlineteachingplatform.entity.dto.CommentDTO;
import com.example.onlineteachingplatform.entity.po.CommentPO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Sheepdog
 */
@Mapper
public interface CommentDao {
    @Insert("""
            INSERT INTO comments (student_id, course_id, content, time)
            VALUES (#{stuId}, #{courseId}, #{content}, #{time})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertComment(CommentPO comment);

    @Select("""
            SELECT U.username AS student_name, C.content As content, C.time AS time
            FROM comments C
            INNER JOIN users U ON C.student_id = U.id
            WHERE course_id = #{courseId}
            ORDER BY time DESC
            """)
    @Results({
            @Result(property = "stuName", column = "student_name"),
            @Result(property = "content", column = "content"),
            @Result(property = "time", column = "time")
    })
    List<CommentDTO> getCommentsByCourseId(Integer courseId);
}
