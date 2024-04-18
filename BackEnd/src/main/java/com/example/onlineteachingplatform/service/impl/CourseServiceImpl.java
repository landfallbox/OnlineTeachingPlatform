package com.example.onlineteachingplatform.service.impl;

import com.example.onlineteachingplatform.dao.UserDao;
import com.example.onlineteachingplatform.entity.dto.CourseSelectionDTO;
import com.example.onlineteachingplatform.entity.po.CoursePO;
import com.example.onlineteachingplatform.entity.vo.CourseVO;
import com.example.onlineteachingplatform.entity.po.CourseSelectionPO;
import com.example.onlineteachingplatform.dao.CourseDao;
import com.example.onlineteachingplatform.entity.vo.CourseSelectionVO;
import com.example.onlineteachingplatform.mapper.CourseMapper;
import com.example.onlineteachingplatform.service.CourseService;
import jakarta.annotation.Resource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Samoye
 * @date 2023/6/26 026 15:44
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseDao courseDao;
    @Resource
    private UserDao userDao;

    /**
     * 创建课程
     */
    @Override
    @Transactional
    public CourseVO createCourse(String courseName, Integer teacherId, LocalDateTime beginTime, LocalDateTime endTime,
                                 MultipartFile video) {
        // 插入信息
        CoursePO coursePo = new CoursePO();
        coursePo.setName(courseName);
        coursePo.setTeacherId(teacherId);
        coursePo.setBeginTime(beginTime);
        coursePo.setEndTime(endTime);
        int rowsChanged = courseDao.insertCourse(coursePo);

        // 插入失败
        if (rowsChanged != 1) {
            return null;
        }

        // 保存视频
        boolean success = saveVideo(video, coursePo.getName());

        if (success) {
            String teacherName = userDao.selectNameById(teacherId);
            CourseVO courseVo = CourseMapper.INSTANCE.toCourseVO(coursePo);
            courseVo.setTeacherName(teacherName);

            return courseVo;
        } else {
            throw new RuntimeException("保存视频失败 回滚数据库");
        }
    }

    /**
     * 更新课程
     *
     * @param courseName 课程名
     * @param teacherId  教师 id
     * @param beginTime  开始时间
     * @param endTime    结束时间
     * @param video      视频
     * @return 更新后的课程信息
     */
    @Override
    public CourseVO updateCourse(String courseName, Integer teacherId, LocalDateTime beginTime, LocalDateTime endTime,
                                 MultipartFile video) {
        // 更新数据库
        CoursePO coursePo = new CoursePO();
        coursePo.setName(courseName);
        coursePo.setTeacherId(teacherId);
        coursePo.setBeginTime(beginTime);
        coursePo.setEndTime(endTime);
        int rowsChanged = courseDao.updateCourse(coursePo);

        // 更新失败
        if (rowsChanged != 1) {
            return null;
        }

        // 保存视频
        boolean success = saveVideo(video, coursePo.getName());

        if (success) {
            String teacherName = userDao.selectNameById(teacherId);
            CourseVO courseVo = CourseMapper.INSTANCE.toCourseVO(coursePo);
            courseVo.setTeacherName(teacherName);

            return courseVo;
        } else {
            throw new RuntimeException("保存视频失败 回滚数据库");
        }
    }


    /**
     * 保存 MultipartFile 到 /static/videos/ 目录下
     */
    private boolean saveVideo(MultipartFile file, String courseName) {
        if (file != null) {
            try {
                // 视频保存相对路径 视频名与对应课程名相同
                String videoPath = "\\src\\main\\resources\\static\\videos\\";
                // 项目的根路径
                String projectRootPath = System.getProperty("user.dir");
                // 视频保存绝对路径
                videoPath = projectRootPath + File.separator + videoPath;

                // 文件后缀
                String originalName = file.getOriginalFilename();
                String ext = "." + FilenameUtils.getExtension(originalName);

                File videoFile = new File(videoPath, courseName + ext);

                // 保存视频
                FileUtils.writeByteArrayToFile(videoFile, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        }

        return false;
    }


    @Override
    public CourseSelectionVO stuSelectCourse(CourseSelectionDTO courseSelectionDto) {
        CourseSelectionPO courseSelectionPo = CourseMapper.INSTANCE.toCourseSelectionPo(courseSelectionDto);

        int rowsChanged = courseDao.insertCourseSelection(courseSelectionPo);

        // 插入失败
        if (rowsChanged != 1) {
            return null;
        }

        return CourseMapper.INSTANCE.toCourseSelectionVO(courseSelectionPo);
    }

    /**
     * 学生退课
     *
     * @param courseSelectionDto 学生课程关系
     * @return 退课后的课程信息
     */
    @Override
    public CourseSelectionVO stuDeselectCourse(CourseSelectionDTO courseSelectionDto) {
        CourseSelectionPO courseSelectionPo = CourseMapper.INSTANCE.toCourseSelectionPo(courseSelectionDto);

        int rowsChanged = courseDao.deleteCourseSelection(courseSelectionPo);

        if (rowsChanged != 1) {
            return null;
        }

        return CourseMapper.INSTANCE.toCourseSelectionVO(courseSelectionPo);
    }

    /**
     * 根据课程 id 获取课程名
     *
     * @param courseId 课程 id
     * @return 课程名
     */
    @Override
    public String getCourseNameById(Integer courseId) {
        return courseDao.selectNameById(courseId);
    }

    /**
     * 根据课程 id 获取单个课程相关信息
     *
     * @param courseId 课程 id
     * @return 课程相关信息
     */
    @Override
    public CourseVO getCourseById(Integer courseId) {
        return courseDao.getCourseById(courseId);
    }

    /**
     * 获取所有课程列表并根据学生 id 标记已选课程
     *
     * @param stuId 学生 id
     * @return 所有课程列表 包含是否已选
     */
    @Override
    public List<CourseVO> getAllCourses(Integer stuId) {
        return courseDao.getAllCourses(stuId);
    }

    /**
     * 根据学生 id 获取已选课程列表
     *
     * @param stuId 学生 id
     * @return 已选课程列表
     */
    @Override
    public List<CourseVO> getSelectedCourses(Integer stuId) {
        List<CourseVO> selectedCoursesList = courseDao.getSelectedCourses(stuId);

        for (CourseVO course : selectedCoursesList) {
            course.setSelected(1);
        }

        return selectedCoursesList;
    }

    /**
     * 根据学生 id 课程 id 获取除此课程以外的其他已选课程列表
     *
     * @param stuId    学生 id
     * @param courseId 课程 id
     * @return 除此课程以外的其他已选课程列表
     */
    @Override
    public List<CourseVO> getOtherSelectedCourses(Integer stuId, Integer courseId) {
        return courseDao.getOtherSelectedCourses(stuId, courseId);

    }


    /**
     * 根据教师 id 获取教师创建的课程列表
     */
    @Override
    public List<CourseVO> getCreatedCourses(Integer teacherId) {
        return courseDao.selectCreatedCourses(teacherId);
    }

}
