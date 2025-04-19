package com.rnyd.rnyd.service.workOutService;

import com.rnyd.rnyd.dto.workout.WorkOutDTO;
import com.rnyd.rnyd.mapper.workOut.WorkOutMapper;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.model.WorkoutEntity;
import com.rnyd.rnyd.repository.user.UserRepository;
import com.rnyd.rnyd.repository.workout.WorkOutRepository;
import com.rnyd.rnyd.service.use_case.WorkOutUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public String updateWorkout(WorkOutDTO workOutDTO){
        if(getWorkOutById(workOutDTO.getWorkoutId()) == null)
            return null;

        workOutRepository.save(workOutMapper.toEntity(workOutDTO));

        return WORKOUT_UPDATED;
    }

    public String createWorkout(WorkOutDTO workOutDTO){
        workOutRepository.save(workOutMapper.toEntity(workOutDTO));

        return WORKOUT_CREATED;
    }

    public String assignWorkout(String email, WorkOutDTO workOutDTO){
        if(getWorkOutById(workOutDTO.getWorkoutId()) == null)
            return null;

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        if(userEntityOptional.isEmpty())
            return null;

        UserEntity user = userEntityOptional.get();
        user.setWorkout(workOutMapper.toEntity(workOutDTO));
        userRepository.save(user);

        return WORKOUT_ASSIGNED;
    }

    public String deleteWorkout(Long id){
        if(getWorkOutById(id) == null)
            return null;

        workOutRepository.deleteById(id);

        return WORKOUT_DELETED;
    }

    public WorkOutDTO getWorkOutById(Long id){
        Optional<WorkoutEntity> optionalWorkoutEntity = workOutRepository.findById(id);
        return workOutMapper.toDto(optionalWorkoutEntity.orElse(null));
    }

    public List<WorkOutDTO> getAllWorkouts(){
        return workOutRepository.findAll().stream().map(workOutMapper::toDto).toList();
    }

}