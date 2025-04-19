package com.rnyd.rnyd.repository.user;

import com.rnyd.rnyd.model.UserMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMeasurementRepository extends JpaRepository<UserMeasurementEntity, Long> {
    List<UserMeasurementEntity> findByUserIdOrderByDateDesc(Long userId);
}
