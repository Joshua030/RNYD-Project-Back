package com.rnyd.rnyd.repository.diet;


import com.rnyd.rnyd.model.DietEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DietRepository extends JpaRepository<DietEntity, Long> {
}
