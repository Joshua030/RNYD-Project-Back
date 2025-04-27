package com.rnyd.rnyd.repository.user;

import com.rnyd.rnyd.model.UserProgressEntity;
import com.rnyd.rnyd.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgressEntity, Long> {

    List<UserProgressEntity> findByUserOrderByProgressDateDesc(UserEntity user);

    Optional<UserProgressEntity> findByUserAndProgressDate(UserEntity user, LocalDate progressDate);

    List<UserProgressEntity> findByUserEmail(String email);
}
