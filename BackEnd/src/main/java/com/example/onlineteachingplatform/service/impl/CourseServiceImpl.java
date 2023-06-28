package com.example.onlineteachingplatform.service.impl;

import com.example.onlineteachingplatform.entity.dto.CourseDTO;
import com.example.onlineteachingplatform.entity.dto.CourseSelectionDTO;
import com.example.onlineteachingplatform.entity.vo.CourseVO;
import com.example.onlineteachingplatform.entity.po.CourseSelectionPO;
import com.example.onlineteachingplatform.dao.CourseDao;
import com.example.onlineteachingplatform.entity.vo.CourseSelectionVO;
import com.example.onlineteachingplatform.mapper.CourseMapper;
import com.example.onlineteachingplatform.service.CourseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Samoye
 * @date 2023/6/26 026 15:44
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseDao courseDao;

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
     * @param courseId 课程 id
     * @return 课程名
     */
    @Override
    public String getCourseNameById(Integer courseId) {
        return courseDao.selectNameById(courseId);
    }

    /**
     * 根据课程 id 获取单个课程相关信息
     * @param courseId 课程 id
     * @return 课程相关信息
     */
    @Override
    public CourseVO getCourseById(Integer courseId) {
        return courseDao.getCourseById(courseId);
    }

    /**
     * 获取所有课程列表并根据学生 id 标记已选课程
     * @param stuId 学生 id
     * @return 所有课程列表 包含是否已选
     */
    @Override
    public List<CourseVO> getAllCourses(Integer stuId) {
        return courseDao.getAllCourses(stuId);
    }

    /**
     * 根据学生 id 获取已选课程列表
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
     * @param stuId 学生 id
     * @param courseId 课程 id
     * @return 除此课程以外的其他已选课程列表
     */
    @Override
    public List<CourseVO> getOtherSelectedCourses(Integer stuId, Integer courseId) {
        return courseDao.getOtherSelectedCourses(stuId, courseId);

    }

}
