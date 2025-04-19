package com.rnyd.rnyd.mapper.diet;

import com.rnyd.rnyd.dto.diet.DietDTO;
import com.rnyd.rnyd.model.DietEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DietDayMapper.class})
public interface DietMapper {
    DietDTO toDto(DietEntity dietEntity);

    DietEntity toEntity(DietDTO dto);
}
