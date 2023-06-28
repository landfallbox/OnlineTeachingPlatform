package com.example.onlineteachingplatform.entity.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : Samoye
 * @date : 2023/6/26 026 15:44
 */
@Data
@AllArgsConstructor
public class CourseSelectionDTO {
    // 学生 id
    @JsonAlias({"student_id", "studentId", "userId"})
    private Integer studentId;
    // 课程 id
    private Integer courseId;
}
