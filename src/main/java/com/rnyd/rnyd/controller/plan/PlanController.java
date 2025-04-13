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

import static com.rnyd.rnyd.utils.constants.Variables.USER_EMAIL_DOES_NOT_EXISTS;

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
        String response = planService.selectPlan(email, plan.getPlan()) ;
        if(response != null){
            return new ResponseEntity<>(planService.selectPlan(email, plan.getPlan()), HttpStatus.OK);
        }
        return new ResponseEntity<>(USER_EMAIL_DOES_NOT_EXISTS, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/cancel/{email}")
    public ResponseEntity<String> cancelPlan(@PathVariable String email){
        String response = planService.cancelPlan(email) ;
        if(response != null){
            return new ResponseEntity<>("Plan cancelado.", HttpStatus.OK);
        }
        return new ResponseEntity<>(USER_EMAIL_DOES_NOT_EXISTS, HttpStatus.NOT_FOUND);
    }
}
