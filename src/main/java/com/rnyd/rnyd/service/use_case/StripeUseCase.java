package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.stripe.StripeDTO;
import com.rnyd.rnyd.dto.stripe.SubscriptionDTO;
import com.stripe.model.PaymentLink;

import java.util.List;

public interface StripeUseCase {
    String createSubscription(StripeDTO  stripeDTO);

    PaymentLink createPaymentLink(StripeDTO stripeDTO);

    List<SubscriptionDTO> getAllSubscriptions();
}
