package com.rnyd.rnyd.service.userProgressService;

import com.rnyd.rnyd.dto.user.UserProgressDTO;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.model.UserProgressEntity;
import com.rnyd.rnyd.repository.user.UserProgressRepository;
import com.rnyd.rnyd.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.rnyd.rnyd.utils.constants.Variables.*;

@Service
public class UserProgressService {

    private final UserProgressRepository userProgressRepository;

    private final UserRepository userRepository;

    public UserProgressService(UserProgressRepository userProgressRepository, UserRepository userRepository) {
        this.userProgressRepository = userProgressRepository;
        this.userRepository = userRepository;
    }

    public String saveProgress(String userEmail, UserProgressDTO request) {

        Optional<UserEntity> userOpt = userRepository.findByEmail(userEmail);
        if (userOpt.isEmpty()) {
            return null;
        }

        UserEntity user = userOpt.get();
        LocalDate currentDate = request.getProgressDate();
        int month = currentDate.getMonthValue();

        Optional<UserProgressEntity> oldProgress = userProgressRepository.findByUserAndProgressDate(user, request.getProgressDate());

        if (oldProgress.isPresent()) {
            UserProgressEntity progressToUpdate = oldProgress.get();
            progressToUpdate.setImageUrl(request.getImageUrl());
            userProgressRepository.save(progressToUpdate);
            return String.format(OVERWROTE_PROGRESS, currentDate.getMonth().name());
        } else {
            UserProgressEntity newProgress = new UserProgressEntity(user, request.getImageUrl(), request.getWeight(), request.getHeight(), currentDate);
            userProgressRepository.save(newProgress);
            return PROGRESS_UPLOADED;
        }
    }

    public List<UserProgressEntity> getUserProgress(String userEmail) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(userEmail);
        return userOpt.map(userProgressRepository::findByUserOrderByProgressDateDesc).orElse(null);
    }
}
