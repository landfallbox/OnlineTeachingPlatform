package com.example.onlineteachingplatform.entity.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Samoye
 * @date 2023/6/25
 */
@Data
public class CoursePO {
    // 课程 id
    private Integer id;
    // 课程名
    private String name;
    // 教师 id
    private Integer teacherId;
    // 开始时间
    private LocalDateTime beginTime;
    // 结束时间
    private LocalDateTime endTime;
}
