package com.rnyd.rnyd.controller.userMeasurement;

import com.rnyd.rnyd.dto.user.UserMeasurementDTO;
import com.rnyd.rnyd.service.userMeasurement.UserMeasurementServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class UserMeasurementController {

    private final UserMeasurementServiceImpl measurementService;

    public UserMeasurementController(UserMeasurementServiceImpl measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping("/{email}")
    public ResponseEntity<Void> saveMeasurement(@PathVariable String email, @RequestBody UserMeasurementDTO dto) {
        measurementService.saveMeasurement(email, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<UserMeasurementDTO>> getMeasurements(@PathVariable String email) {
        return ResponseEntity.ok(measurementService.getMeasurementsByUser(email));
    }
}
