package com.example.onlineteachingplatform.service;

import com.example.onlineteachingplatform.entity.dto.CourseDTO;
import com.example.onlineteachingplatform.entity.dto.CourseSelectionDTO;
import com.example.onlineteachingplatform.entity.vo.CourseVO;
import com.example.onlineteachingplatform.entity.vo.CourseSelectionVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Samoye
 * @date 2023/6/29
 */
@Service
public interface CourseService {
    /**
     * 创建课程
     * @param courseName 课程名
     * @param teacherId 教师 id
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param video 课程视频
     */
    CourseVO createCourse(String courseName, Integer teacherId, LocalDateTime beginTime, LocalDateTime endTime,
                          MultipartFile video);


    /**
     * 修改课程
     */
    CourseVO updateCourse(String courseName, Integer teacherId, LocalDateTime beginTime, LocalDateTime endTime,
                          MultipartFile video);

    /**
     * 学生选课
     * @param courseSelectionDto 学生课程关系
     */
    CourseSelectionVO stuSelectCourse(CourseSelectionDTO courseSelectionDto);

    /**
     * 学生退课
     * @param courseSelectionDto 学生课程关系
     */
    CourseSelectionVO stuDeselectCourse(CourseSelectionDTO courseSelectionDto);


    /**
     * 根据课程 id 获取单个课程相关信息
     */
    CourseVO getCourseById(Integer courseId);


    /**
     * 根据课程 id 获取课程名
     * @param courseId 课程 id
     * @return 课程名
     */
    String getCourseNameById(Integer courseId);


    /**
     * 获取课程列表并根据学生id标记已选课程
     */
    List<CourseVO> getAllCourses(Integer stuId);

    /**
     * 根据学生 id 获取已选课程列表
     */
    List<CourseVO> getSelectedCourses(Integer stuId);

    /**
     * 根据学生 id 课程 id 获取除此课程以外的其他已选课程列表
     * @param stuId 学生 id
     * @param courseId 课程 id
     * @return 除此课程以外的其他已选课程列表
     */
    List<CourseVO> getOtherSelectedCourses(Integer stuId, Integer courseId);


    /**
     * 根据教师 id 获取教师创建的课程列表
     */
    List<CourseVO> getCreatedCourses(Integer teacherId);
}
