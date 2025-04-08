package com.rnyd.rnyd.controller.userProgress;

import com.rnyd.rnyd.dto.UserProgressRequest;
import com.rnyd.rnyd.model.UserProgressEntity;
import com.rnyd.rnyd.service.userProgressService.UserProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progress")
public class UserProgressController {

    private UserProgressService userProgressService;

    public UserProgressController(UserProgressService userProgressService) {
        this.userProgressService = userProgressService;
    }

    @PostMapping("/upload/{email}")
    public ResponseEntity<String> uploadProgress(@PathVariable String email, @RequestBody UserProgressRequest request) {
        String response = userProgressService.saveProgress(email, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{email}")
    public ResponseEntity<List<UserProgressEntity>> getUserProgress(@PathVariable String email) {
        List<UserProgressEntity> progressList = userProgressService.getUserProgress(email);
        return progressList != null ? ResponseEntity.ok(progressList) : ResponseEntity.notFound().build();
    }
}
