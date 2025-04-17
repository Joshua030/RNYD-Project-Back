package com.rnyd.rnyd.service.stripeService;

import com.rnyd.rnyd.dto.StripeDTO;
import com.rnyd.rnyd.service.use_case.StripeUseCase;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;

import static com.rnyd.rnyd.utils.constants.Variables.API_KEY;
import static com.rnyd.rnyd.utils.constants.Variables.CURRENCY;
import com.stripe.param.PaymentLinkCreateParams.LineItem;

public class StripeService implements StripeUseCase {

    @Override
    public Boolean createSubscription(StripeDTO stripeDTO) {
        // TODO ES DE EJEMPLO, CAMBIAR Y COMO FUNCIONA
        try {
            Stripe.apiKey = System.getenv("STRIPE_SECRET_KEY");

            ProductCreateParams productCreateParams =
                    ProductCreateParams.builder()
                            .setName(stripeDTO.getName())
                            .setDescription(stripeDTO.getDescription())
                            .build();
            Product product = Product.create(productCreateParams);

            PriceCreateParams priceCreateParams =
                    PriceCreateParams.builder()
                            .setProduct(product.getId())
                            .setCurrency(CURRENCY)
                            .setUnitAmount(stripeDTO.getPrice())
                            .setRecurring(
                                    PriceCreateParams.Recurring
                                            .builder()
                                            .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                            .build()
                            )
                            .build();
            Price productPrice = Price.create(priceCreateParams);
            return true;
        }catch (StripeException ex){
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public PaymentLink createPaymentLink(StripeDTO stripeDTO) {
        try {
            Stripe.apiKey = System.getenv("STRIPE_SECRET_KEY");

            PaymentLinkCreateParams paymentLinkCreateParams = PaymentLinkCreateParams.builder()
                    .addLineItem(LineItem.builder()
                            .setPrice(stripeDTO.getPriceId())
                            .setQuantity(1L)
                            .build())
                    .build();
            return PaymentLink.create(paymentLinkCreateParams);
        }catch (StripeException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
