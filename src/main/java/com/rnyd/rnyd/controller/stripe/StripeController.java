package com.rnyd.rnyd.controller.stripe;

import com.rnyd.rnyd.dto.stripe.StripeDTO;
import com.rnyd.rnyd.dto.stripe.StripePaymentHistoryDTO;
import com.rnyd.rnyd.dto.stripe.StripePaymentLinkDTO;
import com.rnyd.rnyd.dto.stripe.SubscriptionDTO;
import com.rnyd.rnyd.service.stripeService.StripeService;
import com.stripe.model.PaymentLink;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.rnyd.rnyd.utils.constants.Variables.SUBSCRIPTION_CREATED;
import static com.rnyd.rnyd.utils.constants.Variables.SUBSCRIPTION_NOT_CREATED;

@RestController
@RequestMapping("/stripe")
public class StripeController {

    private final StripeService stripeService;

    StripeController(StripeService stripeService){
        this.stripeService = stripeService;
    }

    @PostMapping("/create-subscription")
    public ResponseEntity<String> createSubscription(@RequestBody StripeDTO stripeDTO){
        String response = stripeService.createSubscription(stripeDTO);
        if(response != null)
            return new ResponseEntity<>(String.format(SUBSCRIPTION_CREATED, response), HttpStatus.CREATED);
        else
            return new ResponseEntity<>(SUBSCRIPTION_NOT_CREATED, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<StripePaymentLinkDTO> subscribe(@RequestBody StripeDTO stripeDTO){
        PaymentLink paymentLink = stripeService.createPaymentLink(stripeDTO);

        if(paymentLink != null) {
            StripePaymentLinkDTO responseDTO = new StripePaymentLinkDTO(paymentLink.getId(), paymentLink.getUrl());
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionDTO>> getAllSubscriptions(){
         return new ResponseEntity<>(stripeService.getAllSubscriptions(), HttpStatus.OK);
    }

    @GetMapping("/payments")
    public ResponseEntity<List<StripePaymentHistoryDTO>> getPaymentHistory() {
        List<StripePaymentHistoryDTO> payments = stripeService.getPaymentHistory();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

}