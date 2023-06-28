package com.example.onlineteachingplatform.entity.po;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Samoye
 * @date 2023/6/25
 */
@Data
public class CommentPO {
    // 编号
    private Integer id;
    // 评论的发出者，即学生
    @JsonAlias({"stuId", "student_id", "stu_id", "sid"})
    private Integer stuId;
    // 被评价的课程
    @JsonAlias({"courseId", "course_id", "cid"})
    private Integer courseId;
    // 评价内容
    private String content;
    // 评价时间
    @JsonAlias({"time", "comment_time"})
    private LocalDateTime time;
}
