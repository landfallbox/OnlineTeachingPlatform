package com.example.onlineteachingplatform.controller;

import com.example.onlineteachingplatform.service.CourseService;
import com.example.onlineteachingplatform.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Samoye
 * @date 2023/06/27 20:36
 */
@RestController
public class VideoController {
    private final CourseService courseService;

    public VideoController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/video")
    public Result<String> getVideoUrlByCourseId(@RequestParam("courseId") Integer courseId) {
        // 课程名
        String courseName = courseService.getCourseNameById(courseId);

        // 获取课程名失败
        if(courseName == null) {
            return Result.error(11, "get video url fail");
        }

        // 课程视频URL
        String videoUrl = "http://localhost:8080/static/videos/" + courseName + ".mp4";

        return Result.success(10, "get video url success", videoUrl);
    }
}
