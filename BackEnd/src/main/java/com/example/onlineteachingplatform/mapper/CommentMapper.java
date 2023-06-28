package com.example.onlineteachingplatform.mapper;

import com.example.onlineteachingplatform.entity.dto.CommentDTO;
import com.example.onlineteachingplatform.entity.po.CommentPO;
import com.example.onlineteachingplatform.entity.vo.CommentVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author : Samoye
 * @date : 2023/6/28 028 14:26
 */
@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    /**
     * 将 CommentDTO 转换为 CommentPO
     */
    @Mappings({
            @Mapping(source = "stuId", target = "stuId"),
            @Mapping(source = "courseId", target = "courseId"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "time", target = "time")
    })
     CommentPO toCommentPo(CommentDTO commentDto);

    /**
     * 将 CommentPO 转换为 CommentVO
     */
    @Mappings({
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "time", target = "time")
    })
    CommentVO toCommentVo(CommentPO commentPo);
}
