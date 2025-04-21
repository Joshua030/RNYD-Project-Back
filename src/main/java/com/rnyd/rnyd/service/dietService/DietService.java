package com.rnyd.rnyd.service.dietService;

import com.rnyd.rnyd.dto.diet.DietDTO;
import com.rnyd.rnyd.dto.diet.DietPDFDTO;
import com.rnyd.rnyd.dto.diet.PreferencesAndAllergiesDTO;
import com.rnyd.rnyd.mapper.diet.DietMapper;
import com.rnyd.rnyd.model.DietEntity;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.repository.diet.DietRepository;
import com.rnyd.rnyd.repository.user.UserRepository;
import com.rnyd.rnyd.service.use_case.DietUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public DietDTO getDietByEmail(String email) {
        Optional<UserEntity> optionalDietEntity = userRepository.findByEmail(email);
        assert optionalDietEntity.orElse(null) != null;
        return dietMapper.toDto(optionalDietEntity.orElse(null).getDiet());
    }

    @Transactional
    public DietPDFDTO getPdfByEmail(String email) {
        Optional<UserEntity> optionalDietEntity = userRepository.findByEmail(email);
        assert optionalDietEntity.orElse(null) != null;

        return dietMapper.toPDFDto(optionalDietEntity.orElse(null).getDiet());
    }

    public String updateDiet(DietDTO dietDTO){
        if(dietRepository.findById(dietDTO.getDietId()).isEmpty())
            return null;

        dietRepository.save(dietMapper.toEntity(dietDTO));

        return DIET_UPDATED;
    }


    @Transactional
    @Override
    public String updateDietWithPdf(DietPDFDTO dietDTO) {
        if(dietRepository.findById(dietDTO.getDietId()).isEmpty())
            return null;

        dietRepository.save(dietMapper.toEntity(dietDTO));

        return DIET_UPDATED;
    }

    public String createDiet(DietDTO dietDTO) {
        DietEntity dietEntity = dietMapper.toEntity(dietDTO);
        dietEntity.setCreatedAt(LocalDateTime.now());
        dietRepository.save(dietEntity);

        return DIET_CREATED;
    }

    @Transactional
    @Override
    public String createDietWithPdf(DietPDFDTO dietDTO) {
        DietEntity dietEntity = dietMapper.toEntity(dietDTO);
        dietEntity.setDietPdf(dietDTO.getDietPdf());
        dietEntity.setCreatedAt(LocalDateTime.now());
        dietRepository.save(dietEntity);

        return DIET_CREATED;
    }

    @Transactional
    public String assignDiet(String email, DietDTO dietDTO){
        Optional<DietEntity> dietOpt = dietRepository.findById(dietDTO.getDietId());
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);

        if (dietOpt.isEmpty() || userOpt.isEmpty()) {
            return null;
        }

        DietEntity diet = dietOpt.get();
        UserEntity user = userOpt.get();

        user.setDiet(diet);

        userRepository.save(user);
        dietRepository.save(diet);

        return DIET_ASSIGNED;
    }


    public String deleteDiet(Long id){
        if(dietRepository.findById(id).isEmpty())
            return null;

        dietRepository.deleteById(id);

        return DIET_DELETED;
    }

    @Override
    public PreferencesAndAllergiesDTO getPreferencesAndAllergies(String email) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        if(userEntityOptional.isEmpty())
            return null;

        DietEntity diet = userEntityOptional.get().getDiet();

        if(diet == null)
            return null;

        return new PreferencesAndAllergiesDTO(diet.getPreferences(), diet.getAllergies());
    }
}

