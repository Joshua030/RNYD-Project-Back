package com.rnyd.rnyd.controller.plan;

import com.rnyd.rnyd.dto.PlanRequest;
import com.rnyd.rnyd.service.planService.PlanService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plans")
public class PlanController {

    // Refactor posible; Juntar el assign con el change plan
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PatchMapping("/assign/{email}")
    public ResponseEntity<String> assignPlan(@PathVariable String email, @RequestBody PlanRequest plan){
        return new ResponseEntity<>(planService.selectPlan(email, plan.getPlan()), HttpStatus.OK);
    }

    @PatchMapping("/cancel/{email}")
    public ResponseEntity<String> cancelPlan(@PathVariable String email){
        return new ResponseEntity<>(planService.cancelPlan(email), HttpStatus.OK);
    }

    @PatchMapping("/change/{email}")
    public ResponseEntity<String> changePlan(@PathVariable String email, @RequestBody PlanRequest plan){
        return new ResponseEntity<>(planService.selectPlan(email, plan.getPlan()), HttpStatus.OK);
    }


}
