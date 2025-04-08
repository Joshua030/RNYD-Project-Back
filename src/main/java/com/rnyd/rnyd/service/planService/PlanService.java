package com.rnyd.rnyd.service.planService;

import com.rnyd.rnyd.mapper.user.UserMapper;
import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.repository.user.UserRepository;
import com.rnyd.rnyd.service.use_case.PlanSelectionUseCase;
import com.rnyd.rnyd.utils.constants.Plans;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.rnyd.rnyd.utils.constants.Plans.getPlanByName;
import static com.rnyd.rnyd.utils.constants.Variables.USER_EMAIL_DOES_NOT_EXISTS;

@Service
public class PlanService implements PlanSelectionUseCase {

    private final UserRepository userRepository;

    private final UserMapper userMapper;


    public PlanService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public String selectPlan(String email, String plan) {
        UserEntity entity = getUserByEmail(email);
        if (entity == null)
            return USER_EMAIL_DOES_NOT_EXISTS;

        entity.setPlan(getPlanByName(plan));
        userRepository.save(entity);

        return String.format("El nuevo plan asignado es: %s.", plan);
    }

    @Override
    public String cancelPlan(String email) {
        selectPlan(email, Plans.NONE.name());
        return "Plan cancelado.";
    }

    private UserEntity getUserByEmail(String email){
        Optional<UserEntity> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }
}
