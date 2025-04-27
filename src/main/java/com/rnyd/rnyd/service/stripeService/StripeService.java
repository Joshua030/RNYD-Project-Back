package com.rnyd.rnyd.service.stripeService;

import com.rnyd.rnyd.dto.stripe.StripeDTO;
import com.rnyd.rnyd.dto.stripe.StripePaymentHistoryDTO;
import com.rnyd.rnyd.dto.stripe.SubscriptionDTO;
import com.rnyd.rnyd.mapper.SubscriptionMapper;
import com.rnyd.rnyd.model.SubscriptionEntity;
import com.rnyd.rnyd.repository.stripe.SubscriptionRepository;
import com.rnyd.rnyd.service.use_case.StripeUseCase;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.ChargeCollection;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;

import static com.rnyd.rnyd.utils.constants.Variables.CURRENCY;
import com.stripe.param.PaymentLinkCreateParams.LineItem;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
@Service
public class StripeService implements StripeUseCase {

    public StripeService(SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    private SubscriptionRepository subscriptionRepository;
    private SubscriptionMapper subscriptionMapper;

    @Override
    public String createSubscription(StripeDTO stripeDTO) {
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

            SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
            subscriptionEntity.setAmount(stripeDTO.getPrice());
            subscriptionEntity.setCreatedAt(LocalDateTime.now());
            subscriptionEntity.setProductId(productPrice.getId());
            subscriptionEntity.setUpdatedAt(LocalDateTime.now());
            subscriptionEntity.setRecurring(true);
            subscriptionEntity.setName(stripeDTO.getName());
            subscriptionEntity.setDescription(stripeDTO.getDescription());
            subscriptionEntity.setCurrency(CURRENCY);
            subscriptionRepository.save(subscriptionEntity);
            return productPrice.getId();
        }catch (StripeException ex){
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Session createCheckoutSession(StripeDTO stripeDTO) {
        try {
            Stripe.apiKey = System.getenv("STRIPE_SECRET_KEY");

            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                    .setSuccessUrl(stripeDTO.getSuccessUrl())
                    .setCancelUrl(stripeDTO.getCancelUrl())
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setPrice(stripeDTO.getPriceId())
                                    .setQuantity(1L)
                                    .build()
                    )
                    .build();

            return Session.create(params);
        } catch (StripeException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream().map(subscriptionMapper::toDto).toList();
    }

    public List<StripePaymentHistoryDTO> getPaymentHistory() {
        Stripe.apiKey = System.getenv("STRIPE_SECRET_KEY");

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 10);

        List<StripePaymentHistoryDTO> list = new ArrayList<>();
        try {
            ChargeCollection charges = Charge.list(params);
            for (Charge charge : charges.getData()) {
                StripePaymentHistoryDTO dto = new StripePaymentHistoryDTO();
                dto.setId(charge.getId());
                dto.setAmount(charge.getAmount());
                dto.setStatus(charge.getStatus());
                dto.setCreated(Instant.ofEpochSecond(charge.getCreated())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime());
                list.add(dto);
            }
        } catch (StripeException e) {
            e.printStackTrace();
        }

        return list;
    }

}
