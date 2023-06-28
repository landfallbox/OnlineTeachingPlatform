package com.example.onlineteachingplatform.entity.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : Samoye
 * @date : 2023/6/28 028 14:31
 */
@Data
public class CommentVO {
    // 学生名称
    private String stuName;
    // 被评价的课程名称
    private String courseName;
    // 评价内容
    private String content;
    // 评价时间
    private LocalDateTime time;
}
