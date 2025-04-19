package com.rnyd.rnyd.service.userMeasurement;

import com.rnyd.rnyd.dto.user.UserMeasurementDTO;
import com.rnyd.rnyd.mapper.user.UserMeasurementMapper;
import com.rnyd.rnyd.model.UserMeasurementEntity;
import com.rnyd.rnyd.repository.user.UserMeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMeasurementServiceImpl   {

    private final UserMeasurementRepository repository;
    private final UserMeasurementMapper mapper;

    public UserMeasurementServiceImpl(UserMeasurementRepository repository, UserMeasurementMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void saveMeasurement(UserMeasurementDTO dto) {
        UserMeasurementEntity entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    public List<UserMeasurementDTO> getMeasurementsByUser(Long userId) {
        return repository.findByUserIdOrderByDateDesc(userId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
