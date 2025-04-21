package com.rnyd.rnyd.service.userMeasurement;

import com.rnyd.rnyd.dto.user.UserMeasurementDTO;
import com.rnyd.rnyd.mapper.user.UserMeasurementMapper;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.model.UserMeasurementEntity;
import com.rnyd.rnyd.repository.user.UserMeasurementRepository;
import com.rnyd.rnyd.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserMeasurementServiceImpl   {

    private final UserMeasurementRepository repository;
    private final UserMeasurementMapper mapper;
    private final UserRepository userRepository;

    public UserMeasurementServiceImpl(UserMeasurementRepository repository, UserMeasurementMapper mapper,UserRepository userRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public void saveMeasurement(String email, UserMeasurementDTO dto) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<UserMeasurementEntity> existing = repository.findByUser(user);

        UserMeasurementEntity entity = mapper.toEntity(dto);
        entity.setUser(user);

        existing.ifPresent(old -> entity.setId(old.getId())); // sobreescribe si ya existía

        repository.save(entity);
    }

    public List<UserMeasurementDTO> getMeasurementsByUser(String email) {
        return repository.findByUserEmailOrderByDateDesc(email)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
