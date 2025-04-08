package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.StripeDTO;
import com.stripe.model.PaymentLink;

public interface StripeUseCase {
    Boolean createSubscription(StripeDTO  stripeDTO);

    PaymentLink createPaymentLink(StripeDTO stripeDTO);
}
