package com.rnyd.rnyd.repository.diet;

import com.rnyd.rnyd.model.DietDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DietDayRepository extends JpaRepository<DietDayEntity, Long> {
}
