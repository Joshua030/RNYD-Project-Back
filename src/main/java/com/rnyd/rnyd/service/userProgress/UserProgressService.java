package com.rnyd.rnyd.service.userProgress;

import com.rnyd.rnyd.dto.UserProgressRequest;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.model.UserProgressEntity;
import com.rnyd.rnyd.repository.user.UserProgressRepository;
import com.rnyd.rnyd.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserProgressService {

    @Autowired
    private UserProgressRepository userProgressRepository;

    @Autowired
    private UserRepository userRepository;

    public String saveProgress(String userEmail, UserProgressRequest request) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(userEmail);
        if (userOpt.isEmpty()) {
            return "Usuario no encontrado.";
        }

        UserEntity user = userOpt.get();
        LocalDate currentDate = request.getProgressDate();
        int month = currentDate.getMonthValue();

        Optional<UserProgressEntity> oldProgress = userProgressRepository.findByUserAndProgressDate(user, request.getProgressDate());

        if (oldProgress.isPresent()) {
            UserProgressEntity progressToUpdate = oldProgress.get();
            progressToUpdate.setImageUrl(request.getImageUrl());
            userProgressRepository.save(progressToUpdate);
            return "Foto del mes " + month + " del año anterior sobrescrita.";
        } else {
            UserProgressEntity newProgress = new UserProgressEntity(user, request.getImageUrl(), request.getWeight(), request.getHeight(), currentDate);
            userProgressRepository.save(newProgress);
            return "Nueva foto de progreso guardada.";
        }
    }

    public List<UserProgressEntity> getUserProgress(String userEmail) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(userEmail);
        return userOpt.map(userProgressRepository::findByUserOrderByProgressDateDesc).orElse(null);
    }
}
