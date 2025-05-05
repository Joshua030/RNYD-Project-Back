package com.rnyd.rnyd.config;

import com.rnyd.rnyd.dto.stripe.StripeDTO;
import com.rnyd.rnyd.model.SubscriptionEntity;
import com.rnyd.rnyd.repository.stripe.SubscriptionRepository;
import com.rnyd.rnyd.service.stripeService.StripeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class StripeInitialSetup {

    private final StripeService stripeService;
    private final SubscriptionRepository subscriptionRepository;

    public StripeInitialSetup(StripeService stripeService, SubscriptionRepository subscriptionRepository) {
        this.stripeService = stripeService;
        this.subscriptionRepository = subscriptionRepository;
    }

    @PostConstruct
    public void setupDefaultSubscriptions() {
        createIfNotExists("Entrenamiento", "Acceso a rutinas de entrenamiento personalizadas cada semana, diseñadas por entrenadores expertos.", 3000L);
        createIfNotExists("Dieta", "Recibe planes de alimentación semanales adaptados a tus objetivos, restricciones y preferencias nutricionales.", 3000L);
        createIfNotExists("Dieta y Entrenamiento", "Incluye rutinas y planes alimenticios completos para transformar tu estilo de vida de forma integral.", 10000L);
    }

    private void createIfNotExists(String name, String description, Long price) {
        boolean exists = subscriptionRepository.existsByName(name);
        if (!exists) {
            StripeDTO dto = new StripeDTO();
            dto.setName(name);
            dto.setDescription(description);
            dto.setPrice(price);
            stripeService.createSubscription(dto);
            System.out.println("Created subscription: " + name);
        } else {
            System.out.println("Subscription already exists: " + name);
        }
    }
}
