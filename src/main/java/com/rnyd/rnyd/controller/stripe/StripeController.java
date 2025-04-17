package com.rnyd.rnyd.controller.stripe;

import com.rnyd.rnyd.dto.StripeDTO;
import com.rnyd.rnyd.service.stripeService.StripeService;
import com.stripe.model.PaymentLink;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.rnyd.rnyd.utils.constants.Variables.SUBSCRIPTION_CREATED;
import static com.rnyd.rnyd.utils.constants.Variables.SUBSCRIPTION_NOT_CREATED;

@RequestMapping("/stripe")
public class StripeController {

    private final StripeService stripeService;

    StripeController(StripeService stripeService){
        this.stripeService = stripeService;
    }

    @PostMapping("/create-subscription")
    public ResponseEntity<String> createSubscription(@RequestBody StripeDTO stripeDTO){
        if(stripeService.createSubscription(stripeDTO))
            return new ResponseEntity<>(SUBSCRIPTION_CREATED, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(SUBSCRIPTION_NOT_CREATED, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<PaymentLink> subscribe(@RequestBody StripeDTO stripeDTO){
        PaymentLink paymentLink = stripeService.createPaymentLink(stripeDTO);

        if(paymentLink != null)
            return new ResponseEntity<>(paymentLink, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}