package com.rnyd.rnyd.service.workOutService;

import com.rnyd.rnyd.dto.diet.DietDTO;
import com.rnyd.rnyd.dto.diet.DietPDFDTO;
import com.rnyd.rnyd.dto.workout.WorkOutDTO;
import com.rnyd.rnyd.dto.workout.WorkOutPDFDTO;
import com.rnyd.rnyd.mapper.workOut.WorkOutMapper;
import com.rnyd.rnyd.model.DietEntity;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.model.WorkoutEntity;
import com.rnyd.rnyd.repository.user.UserRepository;
import com.rnyd.rnyd.repository.workout.WorkOutRepository;
import com.rnyd.rnyd.service.use_case.WorkOutUseCase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.rnyd.rnyd.utils.constants.Variables.*;

@Service
public class WorkOutService implements WorkOutUseCase {
    // TODO: En BBDD hay que cambiar que la tabla nutrition sea independiente
    // y la user tire del id de esta tabla para asignar un entrenamiento

    private final WorkOutRepository workOutRepository;
    private final WorkOutMapper workOutMapper;
    private final UserRepository userRepository;

    public WorkOutService(WorkOutRepository workOutRepository, WorkOutMapper workOutMapper,
            UserRepository userRepository) {
        this.workOutRepository = workOutRepository;
        this.workOutMapper = workOutMapper;
        this.userRepository = userRepository;
    }


    public String createWorkout(WorkOutPDFDTO workoutDTO, MultipartFile dietPdfFile) {
        try {
            String uploadsDir = "public/uploads/";

            String fileName = UUID.randomUUID().toString() + "_" + dietPdfFile.getOriginalFilename();
            Path filePath = Paths.get(uploadsDir, fileName);

            // Guardar el archivo PDF en el servidor
            Files.copy(dietPdfFile.getInputStream(), filePath);

            // Crear la URL pública del archivo PDF
            String dietUrl = "/uploads/" + fileName;

            // Guardar la rutina en la base de datos
            WorkoutEntity workoutEntity = new WorkoutEntity();
            workoutEntity.setWorkoutName(workoutDTO.getWorkoutName());
            workoutEntity.setNote(workoutDTO.getNote());
            workoutEntity.setStartDate(workoutDTO.getStartDate());
            workoutEntity.setCreatedAt(workoutDTO.getCreatedAt());
            // workoutEntity.setWorkoutPdf(dietPdfFile.getBytes());
            workoutEntity.setWorkoutUrl(dietUrl);

            workOutRepository.save(workoutEntity);

            return "Workout created successfully";

        } catch (IOException e) {
            e.printStackTrace();
            return "Error al guardar el PDF";
        }
    }

    @Transactional
    public String updateWorkoutById(Long id, WorkOutDTO dto, MultipartFile pdfFile) {
        Optional<WorkoutEntity> optional = workOutRepository.findById(id);
        if (optional.isEmpty()) return null;
    
        WorkoutEntity workout = optional.get();
        workout.setWorkoutName(dto.getWorkoutName());
        workout.setNote(dto.getNote());
        workout.setStartDate(dto.getStartDate());
        workout.setCreatedAt(dto.getCreatedAt());
    
        if (pdfFile != null && !pdfFile.isEmpty()) {
            try {
                if (workout.getWorkoutUrl() != null) {
                    Path oldFilePath = Paths.get("public", workout.getWorkoutUrl().replace("/uploads/", ""));
                    Files.deleteIfExists(oldFilePath);
                }
    
                String uploadsDir = "public/uploads/";
                String fileName = UUID.randomUUID().toString() + "_" + pdfFile.getOriginalFilename();
                Path filePath = Paths.get(uploadsDir, fileName);
                Files.copy(pdfFile.getInputStream(), filePath);
    
                String workoutUrl = "/uploads/" + fileName;
                workout.setWorkoutUrl(workoutUrl);
            } catch (IOException e) {
                e.printStackTrace();
                return "Error updating workout PDF";
            }
        }
    
        workOutRepository.save(workout);
        return "Workout updated successfully";
    }
    


    // @Transactional
    // public WorkOutPDFDTO getPdfByEmail(String email) {
    // Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
    // assert userEntityOptional.orElse(null) != null;

    // return workOutMapper.toPdfDto(userEntityOptional.orElse(null).getWorkout());
    // }

    @Transactional
    public String assignWorkout(String email, WorkOutDTO workoutDTO) {
        Optional<WorkoutEntity> workoutOpt = workOutRepository.findById(workoutDTO.getWorkoutId());
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);
    
        if (workoutOpt.isEmpty() || userOpt.isEmpty()) {
            return null;
        }
    
        WorkoutEntity workout = workoutOpt.get();
        UserEntity user = userOpt.get();
    
        // Set the workout's user
        workout.setUser(user);
    
        // Add the workout to the user's workouts list if not already there
        if (!user.getWorkouts().contains(workout)) {
            user.getWorkouts().add(workout);
        }
    
        userRepository.save(user); // Saves both due to CascadeType.ALL
    
        return "Workout assigned successfully";
    }

   
    @Transactional
    public String deleteWorkoutById(Long id) {
        Optional<WorkoutEntity> workoutOpt = workOutRepository.findById(id);
    
        if (workoutOpt.isEmpty()) return null;
    
        WorkoutEntity workout = workoutOpt.get();
    
        // Delete PDF file
        if (workout.getWorkoutUrl() != null) {
            Path pdfPath = Paths.get("public", workout.getWorkoutUrl().replace("/uploads/", ""));
            try {
                Files.deleteIfExists(pdfPath);
            } catch (IOException e) {
                e.printStackTrace(); // Optional: Log error
            }
        }
    
        // Delete workout entity
        workOutRepository.delete(workout);
        return "Workout deleted successfully";
    }
    
  

    public List<WorkOutDTO> getAllWorkouts() {
        return workOutRepository.findAll().stream().map(workout -> {
            WorkOutDTO dto = workOutMapper.toDto(workout);
            dto.setWorkoutUrl(workout.getWorkoutUrl());
    
            if (workout.getUser() != null) {
                dto.setUserEmail(workout.getUser().getEmail());
                dto.setUserName(workout.getUser().getName());
            }
    
            return dto;
        }).toList();
    }

}