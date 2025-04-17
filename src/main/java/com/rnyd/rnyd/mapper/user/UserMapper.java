package com.rnyd.rnyd.mapper.user;

import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(UserEntity entity);

    @Mapping(source = "birth_date", target = "birth_date")
    UserEntity toEntity(UserDTO userDTO);

    @Mapping(target = "id", ignore = true) // ignoramos el id para no sobreescribirlo
    void updateUserFromDto(UserDTO dto, @MappingTarget UserEntity entity);
}
