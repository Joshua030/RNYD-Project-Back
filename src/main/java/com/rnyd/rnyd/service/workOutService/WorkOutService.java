package com.rnyd.rnyd.service.workOutService;

import com.rnyd.rnyd.mapper.workOut.WorkOutMapper;
import com.rnyd.rnyd.repository.workout.WorkOutRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkOutService {
    // TODO: En BBDD hay que cambiar que la tabla nutrition sea independiente
    // y la user tire del id de esta tabla para asignar un entrenamiento

    private final WorkOutRepository workOutRepository;

    private final WorkOutMapper workOutMapper;

    public WorkOutService(WorkOutRepository workOutRepository, WorkOutMapper workOutMapper) {
        this.workOutRepository = workOutRepository;
        this.workOutMapper = workOutMapper;
    }

    public Void getDietByUserEmail(){
        return null;
    }

    public Void updateDiet(){
        return null;
    }

    public Void createDiet(){
        return null;
    }

    public Void assignDiet(){
        return null;
    }

    public Void deleteDiet(){
        return null;
    }
}