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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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


    public WorkOutService(WorkOutRepository workOutRepository, WorkOutMapper workOutMapper, UserRepository userRepository) {
        this.workOutRepository = workOutRepository;
        this.workOutMapper = workOutMapper;
        this.userRepository = userRepository;
    }

    public String updateWorkout(String email, WorkOutDTO workOutDTO){
        if(getWorkOutByEmail(email) == null)
            return null;

        workOutRepository.save(workOutMapper.toEntity(workOutDTO));

        return WORKOUT_UPDATED;
    }

    @Transactional
    public String updateWorkoutWithPdf(String email, WorkOutPDFDTO workOutDTO){
        if(getWorkOutByEmail(email) == null)
            return null;

        workOutDTO.setWorkoutId(Objects.requireNonNull(userRepository.findByEmail(email).orElse(null)).getWorkout().getWorkoutId());
        workOutRepository.save(workOutMapper.toEntity(workOutDTO));

        return WORKOUT_UPDATED;
    }

    public String createWorkout(WorkOutDTO workOutDTO){
        workOutRepository.save(workOutMapper.toEntity(workOutDTO));

        return WORKOUT_CREATED;
    }

    public String createWorkoutWithPdf(WorkOutPDFDTO workoutDTO, MultipartFile dietPdfFile ) {
        try {
            String uploadsDir = "public/uploads/";

            String fileName = UUID.randomUUID().toString() + "_" + dietPdfFile.getOriginalFilename();
            Path filePath = Paths.get(uploadsDir, fileName);

            // Guardar el archivo PDF en el servidor
            Files.copy(dietPdfFile.getInputStream(), filePath);

            // Crear la URL pública del archivo PDF
            String dietUrl = "/uploads/" + fileName;

            // Guardar la dieta en la base de datos
            WorkoutEntity dietEntity = new WorkoutEntity();
            dietEntity.setWorkoutName(workoutDTO.getWorkoutName());
            dietEntity.setNote(workoutDTO.getNote());
            dietEntity.setWorkoutPdf(dietPdfFile.getBytes());  // Guardar el archivo PDF
            dietEntity.setWorkoutUrl(dietUrl);  // Guardar la URL
            workOutRepository.save(dietEntity);

            return "Workout created successfully";  // O el mensaje que prefieras

        } catch (IOException e) {
            e.printStackTrace();
            return "Error al guardar el PDF";
        }
    }


    @Transactional
    public WorkOutPDFDTO getPdfByEmail(String email) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        assert userEntityOptional.orElse(null) != null;

        return workOutMapper.toPdfDto(userEntityOptional.orElse(null).getWorkout());
    }

    public String assignWorkout(String email, WorkOutDTO workOutDTO){
        Optional<WorkoutEntity> optionalWorkoutEntity = workOutRepository.findById(workOutDTO.getWorkoutId());
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        if (optionalWorkoutEntity.isEmpty() || userEntityOptional.isEmpty()) {
            return null;
        }

        WorkoutEntity diet = optionalWorkoutEntity.get();
        UserEntity user = userEntityOptional.get();

        user.setWorkout(diet);
        userRepository.save(user);
        workOutRepository.save(diet);
        return WORKOUT_ASSIGNED;
    }

    @Transactional
    public String deleteWorkout(String id){
        UserEntity user = userRepository.findByEmail(id).orElse(null);

        if (user == null || user.getWorkout() == null)
            return null;

        user.setWorkout(null);
        userRepository.save(user);


        return WORKOUT_DELETED;
    }

    public WorkOutDTO getWorkOutByEmail(String email){
        Optional<UserEntity> optionalDietEntity = userRepository.findByEmail(email);
        assert optionalDietEntity.orElse(null) != null;
        return workOutMapper.toDto(optionalDietEntity.orElse(null).getWorkout());
    }

    public List<WorkOutDTO> getAllWorkouts(){
        List<WorkoutEntity> dietEntities = workOutRepository.findAll();

        // Mapear las entidades a DTOs y asignar el URL del PDF
        List<WorkOutDTO> dietDTOList = dietEntities.stream()
                .map(dietEntity -> {
                    WorkOutDTO dietDTO = workOutMapper.toDto(dietEntity);

                    // Asignar la URL del PDF si está disponible
                    dietDTO.setWorkoutUrl("http://localhost:8080"+dietEntity.getWorkoutUrl());  // Este campo debe existir en el DTO

                    return dietDTO;
                })
                .toList();

        return dietDTOList;
    }

}