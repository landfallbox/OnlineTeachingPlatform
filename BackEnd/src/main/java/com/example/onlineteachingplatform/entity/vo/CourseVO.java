package com.example.onlineteachingplatform.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Samoye
 */
@Data
public class CourseVO {
    // 课程 id
    private Integer id;

    // 课程名
    private String name;

    // 开始时间
    private LocalDateTime beginTime;

    // 结束时间
    private LocalDateTime endTime;

    // 教师名
    private String teacherName;

    // 是否已选
    private Integer selected;
}
