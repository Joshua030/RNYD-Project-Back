package com.rnyd.rnyd.mapper.user;

import com.rnyd.rnyd.dto.user.UserMeasurementDTO;
import com.rnyd.rnyd.model.UserMeasurementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMeasurementMapper {

    UserMeasurementEntity toEntity(UserMeasurementDTO dto);

    UserMeasurementDTO toDto(UserMeasurementEntity entity);
}
