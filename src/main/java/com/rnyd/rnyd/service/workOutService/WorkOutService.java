package com.rnyd.rnyd.service.workOutService;

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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Transactional
    public String createWorkoutWithPdf(WorkOutPDFDTO workOutDTO){
        WorkoutEntity workoutEntity = workOutMapper.toEntity(workOutDTO);
        workoutEntity.setWorkoutPdf(workOutDTO.getWorkoutPdf());
        workoutEntity.setCreatedAt(LocalDateTime.now());
        workOutRepository.save(workoutEntity);
        return WORKOUT_CREATED;
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
        return workOutRepository.findAll().stream().map(workOutMapper::toDto).toList();
    }

}