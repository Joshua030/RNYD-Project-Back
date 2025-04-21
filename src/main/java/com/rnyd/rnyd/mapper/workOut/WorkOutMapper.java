package com.rnyd.rnyd.mapper.workOut;

import com.rnyd.rnyd.dto.workout.WorkOutDTO;
import com.rnyd.rnyd.dto.workout.WorkOutPDFDTO;
import com.rnyd.rnyd.model.WorkoutEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkOutMapper {
    WorkOutDTO toDto(WorkoutEntity dietEntity);

    WorkoutEntity toEntity(WorkOutDTO dto);

    @Mapping(target = "workoutPdf", source = "workoutPdf")
    WorkOutPDFDTO toPdfDto(WorkoutEntity dietEntity);

    WorkoutEntity toEntity(WorkOutPDFDTO dto);
}
