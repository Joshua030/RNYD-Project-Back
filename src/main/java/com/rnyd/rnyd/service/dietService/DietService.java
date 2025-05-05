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

    private final DietRepository dietRepository;

    private final DietMapper dietMapper;

    private final UserRepository userRepository;

    public DietService(DietRepository dietRepository, DietMapper dietMapper, UserRepository userRepository) {
        this.dietRepository = dietRepository;
        this.dietMapper = dietMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<DietDTO> getAllDietsWithUsers() {
        return dietRepository.findAll().stream()
                .map(diet -> {
                    DietDTO dto = dietMapper.toDto(diet);
                    dto.setDietUrl(diet.getDietUrl());
                    if (diet.getUser() != null) {
                        dto.setUserEmail(diet.getUser().getEmail());
                        dto.setUserName(diet.getUser().getName());
                    }
                    return dto;
                })
                .toList();
    }

    public List<DietDTO> getDietsByEmailWithUser(String email) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty())
            return List.of();

        UserEntity user = userOpt.get();
        return user.getDiets().stream()
                .map(diet -> {
                    DietDTO dto = dietMapper.toDto(diet);
                    dto.setDietUrl(diet.getDietUrl());
                    dto.setUserEmail(user.getEmail());
                    dto.setUserName(user.getName());
                    return dto;
                })
                .toList();
    }

    public List<DietDTO> getDietsWithoutUser() {
        return dietRepository.findAll().stream()
                .filter(diet -> diet.getUser() == null)
                .map(diet -> {
                    DietDTO dto = dietMapper.toDto(diet);
                    dto.setDietUrl(diet.getDietUrl());
                    return dto;
                })
                .toList();
    }

    @Transactional
    public List<DietDTO> getDietsByEmail(String email) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty())
            return List.of();

        return userOpt.get().getDiets().stream()
                .map(diet -> {
                    DietDTO dto = dietMapper.toDto(diet);
                    dto.setDietUrl(diet.getDietUrl());
                    return dto;
                })
                .toList();
    }

    @Transactional
    public String createDiet(DietDTO dietDTO, MultipartFile dietPdfFile) {
        try {
            // Crear un nombre único para el archivo PDF
            String uploadsDir = "public/uploads/";

            String fileName = UUID.randomUUID().toString() + "_" + dietPdfFile.getOriginalFilename();
            Path filePath = Paths.get(uploadsDir, fileName);

            // Guardar el archivo PDF en el servidor
            Files.copy(dietPdfFile.getInputStream(), filePath);

            // Crear la URL pública del archivo PDF
            String dietUrl = "/uploads/" + fileName;

            // Map DTO to Entity
            DietEntity dietEntity = new DietEntity();
            dietEntity.setDietName(dietDTO.getDietName());
            dietEntity.setNote(dietDTO.getNote());
            dietEntity.setStartDate(dietDTO.getStartDate());
            dietEntity.setCreatedAt(dietDTO.getCreatedAt());
            dietEntity.setPreferences(dietDTO.getPreferences());
            dietEntity.setAllergies(dietDTO.getAllergies());
            dietEntity.setDietPdf(dietPdfFile.getBytes());
            dietEntity.setDietUrl(dietUrl);

            // Save to DB
            dietRepository.save(dietEntity);

            return "Diet created successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error while saving the diet PDF.";
        }
    }

    @Transactional
    public String updateDietWithOptionalPdf(Long dietId, DietDTO dietDTO, MultipartFile dietPdfFile) {
        Optional<DietEntity> dietOpt = dietRepository.findById(dietId);
        if (dietOpt.isEmpty())
            return null;

        DietEntity diet = dietOpt.get();

        // Update basic fields
        diet.setDietName(dietDTO.getDietName());
        diet.setNote(dietDTO.getNote());
        diet.setStartDate(dietDTO.getStartDate());
        diet.setCreatedAt(dietDTO.getCreatedAt());
        diet.setPreferences(dietDTO.getPreferences());
        diet.setAllergies(dietDTO.getAllergies());

        // Handle PDF replacement if a new one is provided
        if (dietPdfFile != null && !dietPdfFile.isEmpty()) {
            // Delete the old file from the file system
            try {
                if (diet.getDietUrl() != null) {
                    Path oldFilePath = Paths.get("public", diet.getDietUrl().replace("/uploads/", ""));
                    Files.deleteIfExists(oldFilePath);
                }

                // Save the new PDF
                String uploadsDir = "public/uploads/";
                String fileName = UUID.randomUUID().toString() + "_" + dietPdfFile.getOriginalFilename();
                Path filePath = Paths.get(uploadsDir, fileName);
                Files.copy(dietPdfFile.getInputStream(), filePath);

                String dietUrl = "/uploads/" + fileName;
                diet.setDietUrl(dietUrl);
                diet.setDietPdf(dietPdfFile.getBytes());

            } catch (IOException e) {
                e.printStackTrace();
                return "Error while updating the diet PDF.";
            }
        }

        dietRepository.save(diet);
        return DIET_UPDATED;
    }

    @Transactional
    public String assignDiet(String email, DietDTO dietDTO) {
        Optional<DietEntity> dietOpt = dietRepository.findById(dietDTO.getDietId());
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);

        if (dietOpt.isEmpty() || userOpt.isEmpty()) {
            return null;
        }

        DietEntity diet = dietOpt.get();
        UserEntity user = userOpt.get();

        // ✅ Set the owning side of the relationship
        diet.setUser(user);

        // Avoid duplicates if already assigned
        if (!user.getDiets().contains(diet)) {
            user.getDiets().add(diet);
        }

        userRepository.save(user); // saves both due to CascadeType.ALL

        return DIET_ASSIGNED;
    }

    public DietDTO getDietById(Long id) {
        Optional<DietEntity> dietOpt = dietRepository.findById(id);

        if (dietOpt.isEmpty())
            return null;

        DietDTO dto = dietMapper.toDto(dietOpt.get());

        return dto;
    }

    @Transactional
    public boolean deleteDietById(Long id) {
        Optional<DietEntity> dietOpt = dietRepository.findById(id);
        if (dietOpt.isEmpty())
            return false;

        DietEntity diet = dietOpt.get();

        // Optionally delete the PDF file
        if (diet.getDietUrl() != null) {
            try {
                Path filePath = Paths.get("public", diet.getDietUrl().replace("/uploads/", ""));
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Remove association from user
        if (diet.getUser() != null) {
            UserEntity user = diet.getUser();
            user.getDiets().remove(diet);
            diet.setUser(null);
        }

        dietRepository.delete(diet);
        return true;
    }

}
