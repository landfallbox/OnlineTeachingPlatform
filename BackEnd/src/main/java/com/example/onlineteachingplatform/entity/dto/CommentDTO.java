package com.example.onlineteachingplatform.entity.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Samoye
 */
@Data
public class CommentDTO {
    // 学生 id
    private Integer stuId;
    // 学生名称
    private String stuName;
    // 被评价的课程 id
    @JsonAlias({"courseId", "course_id", "cid"})
    private Integer courseId;
    // 被评价的课程名称
    private String courseName;
    // 评价内容
    private String content;
    // 评价时间
    @JsonAlias({"time", "comment_time"})
    private LocalDateTime time;
}
