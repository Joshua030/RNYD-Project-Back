package com.rnyd.rnyd.mapper.diet;

import com.rnyd.rnyd.dto.DietDTO;
import com.rnyd.rnyd.model.DietEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DietMapper {
    DietDTO toDto(DietEntity dietEntity);

    DietEntity toEntity(DietDTO dto);
}
