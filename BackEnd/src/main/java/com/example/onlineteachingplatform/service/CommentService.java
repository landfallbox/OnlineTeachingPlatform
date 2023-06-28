package com.example.onlineteachingplatform.service;

import com.example.onlineteachingplatform.entity.dto.CommentDTO;
import com.example.onlineteachingplatform.entity.vo.CommentVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Samoye
 */
@Service
public interface CommentService {
    /**
     * 添加一条评论
     */
    CommentVO add(CommentDTO comment);

    /**
     * 根据课程 id 获取评论列表
     */
    List<CommentDTO> getCommentListByCourseId(Integer courseId);
}
