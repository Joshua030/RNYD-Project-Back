package com.rnyd.rnyd.repository.stripe;

import com.rnyd.rnyd.model.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, String> {

}
