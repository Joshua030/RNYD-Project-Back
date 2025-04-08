package com.rnyd.rnyd.service.use_case;
import com.rnyd.rnyd.utils.constants.Plans;

public interface PlanSelectionUseCase {

    String selectPlan(String email, String plan);

    String cancelPlan(String email);
}
