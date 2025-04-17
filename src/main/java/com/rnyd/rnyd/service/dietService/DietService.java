package com.rnyd.rnyd.service.dietService;

import com.rnyd.rnyd.dto.DietDTO;
import com.rnyd.rnyd.dto.UserDTO;
import com.rnyd.rnyd.mapper.diet.DietMapper;
import com.rnyd.rnyd.model.DietEntity;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.repository.diet.DietRepository;
import com.rnyd.rnyd.repository.user.UserRepository;
import com.rnyd.rnyd.service.use_case.DietUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.rnyd.rnyd.utils.constants.Variables.*;

@Service
public class DietService implements DietUseCase {
    // TODO: En BBDD hay que cambiar que la tabla nutrition sea independiente
    // y la user tire del id de esta tabla para asignar una dieta

    private final DietRepository dietRepository;

    private final DietMapper dietMapper;

    private final UserRepository userRepository;

    public DietService(DietRepository dietRepository, DietMapper dietMapper, UserRepository userRepository) {
        this.dietRepository = dietRepository;
        this.dietMapper = dietMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<DietDTO> getAllDiets() {
        return dietRepository.findAll().stream().map(dietMapper::toDto).toList();
    }

    @Override
    public DietDTO getDietById(Long id) {
        Optional<DietEntity> optionalDietEntity = dietRepository.findById(id);
        return dietMapper.toDto(optionalDietEntity.orElse(null));
    }

    public String updateDiet(DietDTO dietDTO){
        if(getDietById(dietDTO.getDietId()) == null)
            return null;

        dietRepository.save(dietMapper.toEntity(dietDTO));

        return DIET_UPDATED;
    }

    public String createDiet(DietDTO dietDTO){
        if(getDietById(dietDTO.getDietId()) != null)
            return null;

        dietRepository.save(dietMapper.toEntity(dietDTO));

        return DIET_CREATED;
    }

    public String assignDiet(String email, DietDTO dietDTO){
        if(getDietById(dietDTO.getDietId()) == null)
            return null;

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        if(userEntityOptional.isEmpty())
            return null;

        UserEntity user = userEntityOptional.get();
        user.setDiet(dietMapper.toEntity(dietDTO));
        userRepository.save(user);

        return DIET_ASSIGNED;
    }

    public String deleteDiet(Long id){
        if(getDietById(id) == null)
            return null;

        dietRepository.deleteById(id);

        return DIET_DELETED;
    }
}

