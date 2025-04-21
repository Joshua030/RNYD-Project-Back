package com.rnyd.rnyd.mapper.diet;

import com.rnyd.rnyd.dto.diet.DietDTO;
import com.rnyd.rnyd.dto.diet.DietPDFDTO;
import com.rnyd.rnyd.model.DietEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DietMapper {
    DietDTO toDto(DietEntity dietEntity);

    @Mapping(target = "dietPdf", source = "dietPdf")
    DietPDFDTO toPDFDto(DietEntity dietEntity);

    DietEntity toEntity(DietDTO dto);

    @Mapping(target = "dietPdf", source = "dietPdf")
    DietEntity toEntity(DietPDFDTO dto);
}
