package com.rnyd.rnyd.service.dietService;

import com.rnyd.rnyd.dto.DietDTO;
import com.rnyd.rnyd.mapper.diet.DietMapper;
import com.rnyd.rnyd.repository.diet.DietRepository;
import com.rnyd.rnyd.service.use_case.DietUseCase;
import org.springframework.stereotype.Service;

@Service
public class DietService implements DietUseCase {
    // TODO: En BBDD hay que cambiar que la tabla nutrition sea independiente
    // y la user tire del id de esta tabla para asignar una dieta

    private final DietRepository dietRepository;

    private final DietMapper dietMapper;

    public DietService(DietRepository dietRepository, DietMapper dietMapper) {
        this.dietRepository = dietRepository;
        this.dietMapper = dietMapper;
    }

    public DietDTO getDietByUserEmail(String email){
        return null;
    }

    public DietDTO updateDiet(DietDTO dietDTO){
        return null;
    }

    public DietDTO createDiet(DietDTO dietDTO){
        return null;
    }

    public DietDTO assignDiet(String email, DietDTO dietDTO){
        return null;
    }

    public DietDTO deleteDiet(Long id){
        return null;
    }
}

