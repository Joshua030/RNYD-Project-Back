package com.rnyd.rnyd.service.use_case;
import com.rnyd.rnyd.dto.plan.PlanRequest;
import com.rnyd.rnyd.utils.constants.Plans;

import java.util.List;

public interface PlanSelectionUseCase {

    String selectPlan(String email, String plan);

    String cancelPlan(String email);

    List<PlanRequest> getAllPlans();
}
