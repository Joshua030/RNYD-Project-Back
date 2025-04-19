package com.rnyd.rnyd.mapper.diet;

import com.rnyd.rnyd.dto.diet.DietDayDTO;
import com.rnyd.rnyd.model.DietDayEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DietDayMapper {
    DietDayDTO toDto(DietDayEntity entity);
    DietDayEntity toEntity(DietDayDTO dto);
}
