package com.rnyd.rnyd.mapper.user;

import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.dto.request.UserSignUpRequest;
import com.rnyd.rnyd.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "email", target = "email")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "keyword", target = "keyword")
    @Mapping(source = "birth_date", target = "birth_date")
    UserDTO toDto(UserEntity entity);

    @Mapping(source = "email", target = "email")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "keyword", target = "keyword")
    @Mapping(source = "birth_date", target = "birth_date")
    UserEntity toEntity(UserDTO userDTO);

    @Mapping(source = "email", target = "email")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "keyword", target = "keyword")
    @Mapping(source = "birth_date", target = "birth_date")
    UserEntity toEntity(UserSignUpRequest userDTO);
}
