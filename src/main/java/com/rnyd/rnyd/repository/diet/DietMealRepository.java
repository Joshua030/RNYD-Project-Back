package com.rnyd.rnyd.repository.diet;

import com.rnyd.rnyd.model.DietMealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DietMealRepository extends JpaRepository<DietMealEntity, Long> {
}
