package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.stripe.StripeDTO;
import com.rnyd.rnyd.dto.stripe.SubscriptionDTO;
import com.stripe.model.PaymentLink;
import com.stripe.model.checkout.Session;

import java.util.List;

public interface StripeUseCase {
    String createSubscription(StripeDTO  stripeDTO);

    Session createCheckoutSession(StripeDTO stripeDTO);

    List<SubscriptionDTO> getAllSubscriptions();
}
