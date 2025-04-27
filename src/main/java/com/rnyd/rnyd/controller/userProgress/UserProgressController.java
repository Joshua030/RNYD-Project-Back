package com.rnyd.rnyd.controller.userProgress;

import com.rnyd.rnyd.dto.user.UserProgressDTO;
import com.rnyd.rnyd.model.UserProgressEntity;
import com.rnyd.rnyd.service.userProgressService.UserProgressService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

import static com.rnyd.rnyd.utils.constants.Variables.USER_EMAIL_DOES_NOT_EXISTS;

@RestController
@RequestMapping("/progress")
public class UserProgressController {

    private final UserProgressService userProgressService;

    public UserProgressController(UserProgressService userProgressService) {
        this.userProgressService = userProgressService;
    }

    @PostMapping(value = "/upload/{email}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadProgress(
            @PathVariable String email,
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("weight") Double weight,
            @RequestParam("height") Double height,
            @RequestParam("progressDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate progressDate
    ) {
        String response = userProgressService.saveProgress(email, imageFile, weight, height, progressDate);

        if (response != null)
            return ResponseEntity.ok(response);

        return new ResponseEntity<>(USER_EMAIL_DOES_NOT_EXISTS, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/history/{email}")
    public ResponseEntity<List<UserProgressDTO>> getUserProgress(@PathVariable String email) {
        List<UserProgressDTO> progressList = userProgressService.getUserProgress(email);
        return progressList != null ? ResponseEntity.ok(progressList) : ResponseEntity.notFound().build();
    }
}
