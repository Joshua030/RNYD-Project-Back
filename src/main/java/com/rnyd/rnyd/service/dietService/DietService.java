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
        List<DietEntity> dietEntities = dietRepository.findAll();

        // Mapear las entidades a DTOs y asignar el URL del PDF
        List<DietDTO> dietDTOList = dietEntities.stream()
                .map(dietEntity -> {
                    DietDTO dietDTO = dietMapper.toDto(dietEntity);

                    // Asignar la URL del PDF si está disponible
                    dietDTO.setDietUrl("http://localhost:8080"+dietEntity.getDietUrl());  // Este campo debe existir en el DTO

                    return dietDTO;
                })
                .toList();

        return dietDTOList;
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

    public String updateDiet(String email, DietDTO dietDTO){
        if(getDietByEmail(email) == null)
            return null;

        dietRepository.save(dietMapper.toEntity(dietDTO));

        return DIET_UPDATED;
    }


    @Transactional
    @Override
    public String updateDietWithPdf(String email, DietPDFDTO dietDTO) {
        if(getDietByEmail(email) == null)
            return null;


        dietDTO.setDietId(Objects.requireNonNull(userRepository.findByEmail(email).orElse(null)).getDiet().getDietId());
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
    public String createDietWithPdf(DietDTO dietDTO, MultipartFile dietPdfFile) {
        try {
            // Crear un nombre único para el archivo PDF
            String uploadsDir = "public/uploads/";

            String fileName = UUID.randomUUID().toString() + "_" + dietPdfFile.getOriginalFilename();
            Path filePath = Paths.get(uploadsDir, fileName);

            // Guardar el archivo PDF en el servidor
            Files.copy(dietPdfFile.getInputStream(), filePath);

            // Crear la URL pública del archivo PDF
            String dietUrl = "/uploads/" + fileName;

            // Guardar la dieta en la base de datos
            DietEntity dietEntity = new DietEntity();
            dietEntity.setDietName(dietDTO.getDietName());
            dietEntity.setNote(dietDTO.getNote());
            dietEntity.setDietPdf(dietPdfFile.getBytes());  // Guardar el archivo PDF
            dietEntity.setDietUrl(dietUrl);  // Guardar la URL
            dietRepository.save(dietEntity);

            return "Diet created successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error while saving the diet PDF.";
        }
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


    public String deleteDiet(String id){
        UserEntity user = userRepository.findByEmail(id).orElse(null);

        if (user == null || user.getDiet() == null)
            return null;

        user.setDiet(null);
        userRepository.save(user);

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

