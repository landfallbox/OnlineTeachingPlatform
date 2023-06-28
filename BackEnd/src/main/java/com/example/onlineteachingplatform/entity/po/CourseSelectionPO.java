package com.example.onlineteachingplatform.entity.po;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author Samoye
 * @date 2023/6/26 026 15:40
 */
@Data
public class CourseSelectionPO {
    // 选课记录 id
    private Integer id;
    // 学生 id
    @JsonAlias({"student_id", "studentId", "stuId"})
    private Integer stuId;
    // 课程 id
    @JsonAlias({"course_id", "courseId"})
    private Integer courseId;
}
