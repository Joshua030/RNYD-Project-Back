package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.workout.WorkOutDTO;

import java.util.List;

public interface WorkOutUseCase {

     String updateWorkout(String email, WorkOutDTO workOutDTO);
    String createWorkout(WorkOutDTO workOutDTO);
    String assignWorkout(String email, WorkOutDTO workOutDTO);
    String deleteWorkout(String email);
    WorkOutDTO getWorkOutByEmail(String id);
    List<WorkOutDTO> getAllWorkouts();
}
