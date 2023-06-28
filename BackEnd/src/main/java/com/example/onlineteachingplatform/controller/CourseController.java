package com.example.onlineteachingplatform.controller;

import com.example.onlineteachingplatform.entity.dto.CourseSelectionDTO;
import com.example.onlineteachingplatform.entity.vo.CourseVO;
import com.example.onlineteachingplatform.entity.vo.CourseSelectionVO;
import com.example.onlineteachingplatform.service.CourseService;
import com.example.onlineteachingplatform.utils.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Samoye
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 学生选课
     * @param courseSelectionDto 学生课程关系
     * @return 选课结果
     */
    @PostMapping("/select")
    public Result<CourseSelectionVO> select(@RequestBody CourseSelectionDTO courseSelectionDto) {
        CourseSelectionVO courseSelectionVo = courseService.stuSelectCourse(courseSelectionDto);

        if(courseSelectionVo != null) {
            return Result.success(6, "select course success", courseSelectionVo);
        } else {
            return Result.error(7, "select course fail");
        }
    }

    /**
     * 学生退课
     * @param courseSelectionDto 学生课程关系
     * @return 退课结果
     */
    @PostMapping("/deselect")
    public Result<CourseSelectionVO> drop(@RequestBody CourseSelectionDTO courseSelectionDto) {
        CourseSelectionVO courseSelectionVo = courseService.stuDeselectCourse(courseSelectionDto);

        if(courseSelectionVo != null) {
            return Result.success(8, "drop course success", courseSelectionVo);
        } else {
            return Result.error(9, "drop course fail");
        }
    }

    /**
     * 根据课程 id 获取课程信息
     * @param courseId 课程 id
     * @return  课程信息
     */
    @GetMapping("/info")
    public Result<CourseVO> getCourseById(@RequestParam("courseId") Integer courseId) {
        CourseVO course = courseService.getCourseById(courseId);

        if(course != null) {
            return Result.success(12, "get course info successfully", course);
        } else {
            return Result.error(13, "fail to get course info");
        }
    }


    /**
     * 根据学生 id 获取课程列表
     * @param stuId 学生 id
     * @return  学生可选和已选的全部课程
     */
    @GetMapping("/list/all")
    public Result<List<CourseVO>> listAll(@RequestParam("stuId") Integer stuId) {
        List<CourseVO> courseList = courseService.getAllCourses(stuId);

        if(courseList != null) {
            return Result.success(4, "get course list success", courseList);
        } else {
            return Result.error(5, "get course list fail");
        }
    }

    /**
     * 根据学生 id 获取已选课程列表
     * @param stuId 学生 id
     * @return 学生已选的全部课程
     */
    @GetMapping("/list/selected")
    public Result<List<CourseVO>> listSelected(@RequestParam("stuId") Integer stuId) {
        List<CourseVO> courseList = courseService.getSelectedCourses(stuId);

        if(courseList != null) {
            return Result.success(4,  "get course list success", courseList);
        } else {
            return Result.error(5, "get course list fail");
        }
    }

    /**
     * 根据学生 id 课程 id 获取除此课程以外的其他已选课程列表
     * @param stuId 学生 id
     * @param courseId 课程 id
     * @return 除此课程以外的其他已选课程列表
     */
    @GetMapping("/list/selected/other")
    public Result<List<CourseVO>> listSelectedOther(@RequestParam("stuId") Integer stuId,
                                                    @RequestParam("courseId") Integer courseId) {
        List<CourseVO> courseList = courseService.getOtherSelectedCourses(stuId, courseId);

        if(courseList != null) {
            return Result.success(4, "get course list success", courseList);
        } else {
            return Result.error(5, "get course list fail");
        }
    }

}
