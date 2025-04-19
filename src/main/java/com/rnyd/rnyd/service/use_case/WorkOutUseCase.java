package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.workout.WorkOutDTO;

import java.util.List;

public interface WorkOutUseCase {

     String updateWorkout(WorkOutDTO workOutDTO);
    String createWorkout(WorkOutDTO workOutDTO);
    String assignWorkout(String email, WorkOutDTO workOutDTO);
    String deleteWorkout(Long id);
    WorkOutDTO getWorkOutById(Long id);
    List<WorkOutDTO> getAllWorkouts();
}
