package com.rnyd.rnyd.mapper.user;

import com.rnyd.rnyd.dto.user.UserMeasurementDTO;
import com.rnyd.rnyd.model.UserMeasurementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMeasurementMapper {
    UserMeasurementDTO toDto(UserMeasurementEntity entity);
    UserMeasurementEntity toEntity(UserMeasurementDTO dto);
}
