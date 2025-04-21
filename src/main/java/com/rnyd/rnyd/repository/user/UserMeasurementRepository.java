package com.rnyd.rnyd.repository.user;

import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.model.UserMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMeasurementRepository extends JpaRepository<UserMeasurementEntity, Long> {
    List<UserMeasurementEntity> findByUserEmailOrderByDateDesc(String email);
    Optional<UserMeasurementEntity> findByUser(UserEntity user);

}
