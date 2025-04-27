package com.rnyd.rnyd.service.userProgressService;

import com.rnyd.rnyd.dto.user.UserProgressDTO;
import com.rnyd.rnyd.mapper.user.UserProgressMapper;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.model.UserProgressEntity;
import com.rnyd.rnyd.repository.user.UserProgressRepository;
import com.rnyd.rnyd.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.rnyd.rnyd.utils.constants.Variables.*;

@Service
public class UserProgressService {

    private final UserProgressRepository userProgressRepository;

    private final UserRepository userRepository;

    private final UserProgressMapper userProgressMapper;

    public UserProgressService(UserProgressRepository userProgressRepository, UserRepository userRepository,UserProgressMapper userProgressMapper) {
        this.userProgressRepository = userProgressRepository;
        this.userRepository = userRepository;
        this.userProgressMapper = userProgressMapper;
    }

    public String saveProgress(String email, MultipartFile imageFile, Double weight, Double height, LocalDate progressDate) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return null;
        }

        try {
            // Ruta donde guardarás las imágenes
            String uploadsDir = "public/uploads/";
            File uploadsFolder = new File(uploadsDir);
            if (!uploadsFolder.exists()) {
                uploadsFolder.mkdirs();
            }

            // Nombre único para el archivo
            String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(uploadsDir, fileName);

            // Guardar la imagen
            Files.copy(imageFile.getInputStream(), filePath);

            // URL pública que podrás acceder
            String imageUrl = "/uploads/" + fileName;

            // Aquí puedes guardar el progreso asociado al usuario (no te olvides de adaptar tu entidad)
            UserProgressEntity progress = new UserProgressEntity();
            progress.setImageUrl(imageUrl); // este campo debes crearlo
            progress.setWeight(weight);
            progress.setHeight(height);
            progress.setProgressDate(progressDate);
            progress.setUser(userOpt.get());

            userProgressRepository.save(progress);

            return "Guardado correctamente.";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<UserProgressDTO> getUserProgress(String email) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return null;
        }

        List<UserProgressEntity> progressEntities = userProgressRepository.findByUserEmail(email);

        List<UserProgressDTO> dtoList = new ArrayList<>();
        for (UserProgressEntity progress : progressEntities) {
            UserProgressDTO dto = new UserProgressDTO();
            dto.setImagePath(progress.getImageUrl()); // Aquí pones el URL
            dto.setWeight(progress.getWeight());
            dto.setHeight(progress.getHeight());
            dto.setProgressDate(progress.getProgressDate());
            dtoList.add(dto);
        }

        return dtoList;
    }

}
