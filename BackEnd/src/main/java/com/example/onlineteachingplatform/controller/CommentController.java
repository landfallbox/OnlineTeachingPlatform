package com.example.onlineteachingplatform.controller;

import com.example.onlineteachingplatform.entity.dto.CommentDTO;
import com.example.onlineteachingplatform.entity.vo.CommentVO;
import com.example.onlineteachingplatform.service.CommentService;
import com.example.onlineteachingplatform.utils.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sheepdog
 */

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("add")
    public Result<CommentVO> stuCommentCourse(@RequestBody CommentDTO comment){
        CommentVO commentVo = commentService.add(comment);

        if(commentVo != null){
            return Result.success(14, "comment successfully", commentVo);
        }
        else{
            return Result.error(15, "fail to comment");
        }
    }

    @GetMapping("list/all")
    public Result<List<CommentDTO>> getCommentList(@RequestParam("courseId") Integer courseId){
        List<CommentDTO> commentList = commentService.getCommentListByCourseId(courseId);

        if(commentList != null){
            return Result.success(17, "get comment successfully", commentList);
        }
        else{
            return Result.error(16, "fail to get comment");
        }
    }
}
