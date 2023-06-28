package com.example.onlineteachingplatform.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.mapstruct.Mapper;

/**
 * @author : Samoye
 * @date : 2023/6/27 027 14:42
 */
@Data
@AllArgsConstructor
public class CourseDTO {
    // 课程 id
    private Integer id;
    // 课程名
    private String name;
    // 开始时间
    private String beginTime;
    // 结束时间
    private String endTime;
    // 教师 id
    private Integer teacherId;
    // 教师名
    private String teacherName;
    // 是否已选
    private Integer selected;
}
