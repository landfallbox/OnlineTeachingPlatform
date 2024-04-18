package com.example.onlineteachingplatform.dao;

import com.example.onlineteachingplatform.entity.po.CoursePO;
import com.example.onlineteachingplatform.entity.vo.CourseVO;
import com.example.onlineteachingplatform.entity.po.CourseSelectionPO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Samoye
 * @date 2023/6/27 027 11:27
 */
@Mapper
public interface CourseDao {
    /**
     * 创建课程 插入一条课程信息记录
     * @param coursePo 课程信息
     */
    @Insert("""
            INSERT INTO courses (name, begin_time, end_time, teacher_id)
            VALUES (#{name}, #{beginTime}, #{endTime}, #{teacherId})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertCourse(CoursePO coursePo);

    @Insert("""
            INSERT INTO students_courses (student_id, course_id)
            VALUES (#{stuId}, #{courseId})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertCourseSelection(CourseSelectionPO courseSelectionPo);

    @Delete("""
            DELETE FROM students_courses
            WHERE student_id = #{stuId} AND course_id = #{courseId}
            """)
    int deleteCourseSelection(CourseSelectionPO studentCourse);

    /**
     * 根据课程 id 获取课程名
     * @param courseId 课程 id
     * @return 课程名
     */
    @Select("""
            SELECT name
            FROM courses
            WHERE id = #{courseId}
            """)
    String selectNameById(Integer courseId);

    @Select("""
            SELECT C.name AS course_name, C.begin_time, C.end_time, U.username AS teacher_name
            FROM courses C
            INNER JOIN users U ON C.teacher_id = U.id
            WHERE C.id = #{courseId}
            """)
    @Results({
            @Result(property = "name", column = "course_name"),
            @Result(property = "teacherName", column = "teacher_name"),
            @Result(property = "beginTime", column = "begin_time"),
            @Result(property = "endTime", column = "end_time")
    })
    CourseVO getCourseById(Integer courseId);

    /**
     * 获取课程列表并根据学生id标记已选课程
     * @param stuId 学生id
     * @return 课程列表 包含是否已选
     */
    @Select("""
            SELECT C.id AS course_id, C.name AS course_name, C.begin_time, C.end_time, U.username AS teacher_name,
            IF(SC.student_id IS NULL, 0, 1) AS selected
            FROM courses C
            INNER JOIN users U ON C.teacher_id = U.id
            LEFT JOIN students_courses SC ON SC.student_id = #{stuId} AND SC.course_id = C.id
            """)
    @Results({
            @Result(property = "id", column = "course_id"),
            @Result(property = "name", column = "course_name"),
            @Result(property = "teacherName", column = "teacher_name"),
            @Result(property = "beginTime", column = "begin_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "selected", column = "selected")
    })
    List<CourseVO> getAllCourses(Integer stuId);


    @Select("""
            SELECT C.id AS course_id, C.name AS course_name, C.begin_time, C.end_time, U.username AS teacher_name,
            IF(SC.student_id IS NULL, 0, 1) AS selected
            FROM courses C
            INNER JOIN users U ON C.teacher_id = U.id
            INNER JOIN students_courses SC ON SC.student_id = #{stuId} AND SC.course_id = C.id
            """)
    @Results({
            @Result(property = "id", column = "course_id"),
            @Result(property = "name", column = "course_name"),
            @Result(property = "teacherName", column = "teacher_name"),
            @Result(property = "beginTime", column = "begin_time"),
            @Result(property = "endTime", column = "end_time")
    })
    List<CourseVO> getSelectedCourses(Integer stuId);


    /**
     * 根据学生 id 课程 id 获取除此课程外的其他已选课程
     * @param stuId 学生 id
     * @param courseId 课程 id
     * @return 除此课程外的其他已选课程
     */
    @Select("""
            SELECT C.id AS course_id, C.name AS course_name, C.begin_time, C.end_time, U.username AS teacher_name
            FROM courses C
            INNER JOIN users U ON C.teacher_id = U.id
            INNER JOIN students_courses SC ON C.id = SC.course_id
            WHERE SC.student_id = #{stuId} AND SC.course_id != #{courseId};
        """)
    @Results({
            @Result(property = "id", column = "course_id"),
            @Result(property = "name", column = "course_name"),
            @Result(property = "teacherName", column = "teacher_name"),
            @Result(property = "beginTime", column = "begin_time"),
            @Result(property = "endTime", column = "end_time"),
    })
    List<CourseVO> getOtherSelectedCourses(Integer stuId, Integer courseId);


    @Select("""
            SELECT C.id AS id, C.name AS course_name, C.begin_time, C.end_time
            FROM courses C
            WHERE C.teacher_id = #{teacherId}
            """)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "course_name"),
            @Result(property = "beginTime", column = "begin_time"),
            @Result(property = "endTime", column = "end_time")
    })
    List<CourseVO> selectCreatedCourses(Integer teacherId);

    @Update("""
            UPDATE courses
            SET name = #{name}, begin_time = #{beginTime}, end_time = #{endTime}
            WHERE id = #{id}
            """)
    int updateCourse(CoursePO coursePo);
}
