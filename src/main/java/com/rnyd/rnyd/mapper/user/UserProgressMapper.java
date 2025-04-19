package com.rnyd.rnyd.mapper.user;

import com.rnyd.rnyd.dto.user.UserProgressDTO;
import com.rnyd.rnyd.model.UserProgressEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProgressMapper {
    UserProgressDTO toDto(UserProgressEntity entity);
}
