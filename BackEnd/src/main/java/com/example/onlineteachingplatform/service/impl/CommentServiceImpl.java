package com.example.onlineteachingplatform.service.impl;

import com.example.onlineteachingplatform.dao.CourseDao;
import com.example.onlineteachingplatform.dao.UserDao;
import com.example.onlineteachingplatform.entity.dto.CommentDTO;
import com.example.onlineteachingplatform.dao.CommentDao;
import com.example.onlineteachingplatform.entity.dto.CourseSelectionDTO;
import com.example.onlineteachingplatform.entity.po.CommentPO;
import com.example.onlineteachingplatform.entity.vo.CommentVO;
import com.example.onlineteachingplatform.mapper.CommentMapper;
import com.example.onlineteachingplatform.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Samoye
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;
    @Resource
    private CourseDao courseDao;
    @Resource
    private UserDao userDao;

    // 添加一条评论
    @Override
    public CommentVO add(CommentDTO comment) {
        CommentPO commentPo = CommentMapper.INSTANCE.toCommentPo(comment);
        int rowsChanged = commentDao.insertComment(commentPo);

        if(rowsChanged == 1){
            Integer stuId = commentPo.getStuId();
            Integer courseId = commentPo.getCourseId();

            String stuName = userDao.selectNameById(stuId);
            String courseName = courseDao.selectNameById(courseId);

            CommentVO commentVo = CommentMapper.INSTANCE.toCommentVo(commentPo);
            commentVo.setStuName(stuName);
            commentVo.setCourseName(courseName);

            return commentVo;
        }
        else {
            return null;
        }
    }

    // 根据课程 id 获取评论列表
    @Override
    public List<CommentDTO> getCommentListByCourseId(Integer courseId) {
        return commentDao.getCommentsByCourseId(courseId);
    }
}
