package com.rnyd.rnyd.service.planService;

import com.rnyd.rnyd.model.UserEntity;
import com.rnyd.rnyd.repository.user.UserRepository;
import com.rnyd.rnyd.service.use_case.PlanSelectionUseCase;
import com.rnyd.rnyd.utils.constants.Plans;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.rnyd.rnyd.utils.constants.Plans.getPlanByName;

@Service
public class PlanService implements PlanSelectionUseCase {

    private final UserRepository userRepository;

    public PlanService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String selectPlan(String email, String plan) {
        UserEntity entity = getUserByEmail(email);
        if (entity == null)
            return null;

        entity.setPlan(getPlanByName(plan));
        userRepository.save(entity);

        return String.format("El nuevo plan asignado es: %s.", plan);
    }

    @Override
    public String cancelPlan(String email) {
        return selectPlan(email, Plans.NONE.name());
    }

    private UserEntity getUserByEmail(String email){
        Optional<UserEntity> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }
}
