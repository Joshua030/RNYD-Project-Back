package com.rnyd.rnyd.mapper.workOut;

import com.rnyd.rnyd.dto.workout.WorkOutDTO;
import com.rnyd.rnyd.model.WorkoutEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkOutMapper {
    WorkOutDTO toDto(WorkoutEntity dietEntity);

    WorkoutEntity toEntity(WorkOutDTO dto);
}
