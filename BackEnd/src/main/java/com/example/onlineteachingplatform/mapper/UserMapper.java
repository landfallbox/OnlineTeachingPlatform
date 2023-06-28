package com.example.onlineteachingplatform.mapper;

import com.example.onlineteachingplatform.entity.dto.LoginDTO;
import com.example.onlineteachingplatform.entity.dto.RegisterDTO;
import com.example.onlineteachingplatform.entity.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * @author : Samoye
 * @date : 2023/6/25 025 15:57
 */

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * 将 LoginDTO 转换为 UserPO
     */
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "password", target = "password")
    })
    UserPO toUserPo(LoginDTO loginDTO);

    /**
     * 将 UserPO 转换为 LoginDTO
     */
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "password", target = "password")
    })
    LoginDTO toLoginDto(UserPO userPo);

    /**
     * 将 RegisterDto 转换为 UserPO
     */
    @Mappings({
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "role", target = "role")
    })
    UserPO toUserPo(RegisterDTO registerDto);
}
