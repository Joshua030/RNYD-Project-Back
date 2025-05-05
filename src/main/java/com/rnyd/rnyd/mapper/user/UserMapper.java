package com.rnyd.rnyd.mapper.user;

import com.rnyd.rnyd.dto.user.UserDTO;
import com.rnyd.rnyd.mapper.diet.DietMapper;
import com.rnyd.rnyd.mapper.workOut.WorkOutMapper;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.repository.stripe.SubscriptionRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = { WorkOutMapper.class, DietMapper.class, UserProgressMapper.class,
                UserMeasurementMapper.class })
public abstract class UserMapper {

        @Autowired
        protected SubscriptionRepository subscriptionRepository;

        @Mappings({
                        @Mapping(source = "progressList", target = "progressList"),
                        @Mapping(source = "measurements", target = "measurements"),
                        @Mapping(source = "diets", target = "diets"),
                        @Mapping(source = "workouts", target = "workouts"),
                        @Mapping(source = "mealsPerDay", target = "mealsPerDay"),
                        @Mapping(source = "allergies", target = "allergies"),
                        @Mapping(source = "injuries", target = "injuries"),
                        @Mapping(source = "gymGoal", target = "gymGoal"),
                        @Mapping(source = "trainingDays", target = "trainingDays"),
                        @Mapping(target = "subscriptionName", ignore = true),
                        @Mapping(target = "subscriptionAmount", ignore = true),
                        @Mapping(source = "gender", target = "gender"),
                        @Mapping(source = "weight", target = "weight"),
                        @Mapping(source = "height", target = "height"),
                        @Mapping(source = "neck", target = "neck"),
                        @Mapping(source = "shoulders", target = "shoulders"),
                        @Mapping(source = "chest", target = "chest"),
                        @Mapping(source = "waist", target = "waist"),
                        @Mapping(source = "hips", target = "hips"),
                        @Mapping(source = "thigh", target = "thigh"),
                        @Mapping(source = "calf", target = "calf"),

        })
        public abstract UserDTO toDto(UserEntity user);

        @AfterMapping
        protected void enrichWithSubscription(@MappingTarget UserDTO dto, UserEntity user) {
                if (user.getSubscriptionProductId() != null) {
                        subscriptionRepository.findById(user.getSubscriptionProductId()).ifPresent(subscription -> {
                                dto.setSubscriptionName(subscription.getName());
                                dto.setSubscriptionAmount(subscription.getAmount());
                        });
                }
        }

        @Mappings({
                        @Mapping(source = "birth_date", target = "birth_date"),
                        @Mapping(source = "allergies", target = "allergies"),
                        @Mapping(source = "injuries", target = "injuries"),
                        @Mapping(source = "gymGoal", target = "gymGoal"),
                        @Mapping(source = "trainingDays", target = "trainingDays")

        })
        public abstract UserEntity toEntity(UserDTO userDTO);

        @Mapping(target = "id", ignore = true)
        public abstract void updateUserFromDto(UserDTO dto, @MappingTarget UserEntity entity);
}
