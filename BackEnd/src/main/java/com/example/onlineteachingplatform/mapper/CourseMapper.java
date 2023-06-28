package com.example.onlineteachingplatform.mapper;

import com.example.onlineteachingplatform.entity.dto.CourseDTO;
import com.example.onlineteachingplatform.entity.dto.CourseSelectionDTO;
import com.example.onlineteachingplatform.entity.po.CourseSelectionPO;
import com.example.onlineteachingplatform.entity.vo.CourseSelectionVO;
import com.example.onlineteachingplatform.entity.vo.CourseVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author : Samoye
 * @date : 2023/6/26 026 15:55
 */
@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    /**
     * 将 CourseSelectionDTO 转换为 CourseSelectionPO
     */
    @Mappings({
            @Mapping(source = "studentId", target = "stuId"),
            @Mapping(source = "courseId", target = "courseId")
    })
    CourseSelectionPO toCourseSelectionPo(CourseSelectionDTO courseSelectionDto);

    /**
     * 将 CourseSelectionPO 转换为 CourseSelectionVO
     */
    @Mappings({
            @Mapping(source = "stuId", target = "studentId"),
            @Mapping(source = "courseId", target = "courseId")
    })
    CourseSelectionVO toCourseSelectionVO(CourseSelectionPO courseSelectionPo);

    /**
     * 将 List<CourseDTO> 转换为 List<CourseVO>
     */
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "teacherName", target = "teacherName"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "beginTime", target = "beginTime"),
            @Mapping(source = "endTime", target = "endTime"),
    })
    List<CourseVO> toCourseVO(List<CourseDTO> courseDtoList);
}
