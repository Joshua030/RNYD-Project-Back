package com.rnyd.rnyd.mapper;

import com.rnyd.rnyd.dto.stripe.SubscriptionDTO;
import com.rnyd.rnyd.model.SubscriptionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionDTO toDto(SubscriptionEntity subscriptionEntity);

    SubscriptionEntity toEntity(SubscriptionDTO subscriptionDTO);
}
