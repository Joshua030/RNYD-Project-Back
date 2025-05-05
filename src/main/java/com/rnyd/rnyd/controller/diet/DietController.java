package com.rnyd.rnyd.controller.diet;

import com.rnyd.rnyd.dto.diet.DietDTO;
import com.rnyd.rnyd.dto.diet.DietPDFDTO;
import com.rnyd.rnyd.dto.diet.PreferencesAndAllergiesDTO;
import com.rnyd.rnyd.service.dietService.DietService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ContentDisposition;

import java.io.IOException;
import java.util.List;

import static com.rnyd.rnyd.utils.constants.Variables.*;

@RestController
@RequestMapping("/diet")
public class DietController {

    private final DietService dietService;

    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @PostMapping(value = "/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> createDiet(@RequestPart DietDTO dietDTO,
            @RequestParam("dietPdfFile") MultipartFile dietPdfFile) {
        String result = dietService.createDiet(dietDTO, dietPdfFile);

        if (result != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/assign/{email}")
    public ResponseEntity<String> assignDiet(@PathVariable String email, @RequestBody DietDTO dietDTO) {
        String dietResponse = dietService.assignDiet(email, dietDTO);

        if (dietResponse != null) {
            return new ResponseEntity<>(dietResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(DIET_NOT_ASSIGNED, HttpStatus.BAD_REQUEST);
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<DietDTO>> getDiets(@RequestParam(required = false) String email) {
        List<DietDTO> dietResponse;

        if ("none".equalsIgnoreCase(email)) {
            dietResponse = dietService.getDietsWithoutUser();
        } else if (email != null && !email.isBlank()) {
            dietResponse = dietService.getDietsByEmailWithUser(email);
        } else {
            dietResponse = dietService.getAllDietsWithUsers();
        }

        return new ResponseEntity<>(dietResponse, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{email}")
    public ResponseEntity<List<DietDTO>> getDietsByEmail(@PathVariable String email) {
        List<DietDTO> dietResponse = dietService.getDietsByEmail(email);

        if (!dietResponse.isEmpty()) {
            return new ResponseEntity<>(dietResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(dietResponse, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/single/{id}")
    public ResponseEntity<DietDTO> getDietById(@PathVariable Long id) {
        DietDTO diet = dietService.getDietById(id);
        if (diet != null) {
            return new ResponseEntity<>(diet, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping(value = "/update/{dietId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> updateDietWithPdf(
            @PathVariable Long dietId,
            @RequestPart("dietDTO") DietDTO dietDTO,
            @RequestPart(value = "dietPdfFile", required = false) MultipartFile dietPdfFile) {

        String result = dietService.updateDietWithOptionalPdf(dietId, dietDTO, dietPdfFile);

        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diet not found.");
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDiet(@PathVariable Long id) {
        boolean deleted = dietService.deleteDietById(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dieta no encontrada.");
        }
    }

}
