package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.workout.WorkOutDTO;
import com.rnyd.rnyd.dto.workout.WorkOutPDFDTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface WorkOutUseCase {

    //  String updateWorkout(String email, WorkOutDTO workOutDTO);
    String createWorkout(WorkOutPDFDTO workOutDTO, MultipartFile dietPdfFile);
    String assignWorkout(String email, WorkOutDTO workOutDTO);
    String updateWorkoutById(Long id, WorkOutDTO dto, MultipartFile pdfFile);
    String deleteWorkoutById(Long id);
    // WorkOutDTO getWorkOutByEmail(String id);
    List<WorkOutDTO> getAllWorkouts();
}
