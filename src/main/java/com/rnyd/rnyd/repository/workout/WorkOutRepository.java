package com.rnyd.rnyd.repository.workout;

import com.rnyd.rnyd.model.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOutRepository extends JpaRepository<WorkoutEntity, Long> {
}
