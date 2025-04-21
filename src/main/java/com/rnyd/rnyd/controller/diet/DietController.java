package com.rnyd.rnyd.controller.diet;

import com.rnyd.rnyd.dto.diet.DietDTO;
import com.rnyd.rnyd.dto.diet.DietPDFDTO;
import com.rnyd.rnyd.dto.diet.PreferencesAndAllergiesDTO;
import com.rnyd.rnyd.service.dietService.DietService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/create")
    public ResponseEntity<String> createDiet(@RequestBody DietDTO dietDTO){
        String dietResponse = dietService.createDiet(dietDTO);

        if (dietResponse != null) {
            return new ResponseEntity<>(dietResponse, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(DIET_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
    }
    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createDiet(
            @RequestPart("diet") DietPDFDTO dietDTO,
            @RequestPart("dietPdf") MultipartFile pdfFile) {

        try {
            dietDTO.setDietPdf(pdfFile.getBytes());
        } catch (IOException e) {
            return new ResponseEntity<>(ERROR_PDF, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String dietResponse = dietService.createDietWithPdf(dietDTO);

        if (dietResponse != null) {
            return new ResponseEntity<>(dietResponse, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(DIET_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    public ResponseEntity<String> updateDiet(@RequestBody DietDTO dietDTO){
        String dietResponse = dietService.updateDiet(dietDTO);

        if(dietResponse != null){
            return new ResponseEntity<>(dietResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(DIET_NOT_UPDATED, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<String> updateDiet(
            @RequestPart("diet") DietPDFDTO dietDTO,
            @RequestPart("dietPdf") MultipartFile pdfFile) {

        try {
            dietDTO.setDietPdf(pdfFile.getBytes());
        } catch (IOException e) {
            return new ResponseEntity<>(ERROR_PDF, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String dietResponse = dietService.updateDietWithPdf(dietDTO);

        if (dietResponse != null) {
            return new ResponseEntity<>(dietResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(DIET_NOT_UPDATED, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDiet(@PathVariable Long id){
        String dietResponse = dietService.deleteDiet(id);

        if(dietResponse != null){
            return new ResponseEntity<>(dietResponse, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(DIET_NOT_DELETED, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/assign/{email}")
    public ResponseEntity<String> assignDiet(@PathVariable String email, @RequestBody DietDTO dietDTO){
        String dietResponse = dietService.assignDiet(email, dietDTO);

        if(dietResponse != null){
            return new ResponseEntity<>(dietResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(DIET_NOT_ASSIGNED, HttpStatus.BAD_REQUEST);
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<DietDTO>> getAllDiets(){
        List<DietDTO> dietResponse = dietService.getAllDiets();

        if(!dietResponse.isEmpty()){
            return new ResponseEntity<>(dietResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Transactional(readOnly = true)
    @GetMapping("/{email}")
    public ResponseEntity<DietDTO> getDietByEmail(@PathVariable String email){
        DietDTO dietResponse = dietService.getDietByEmail(email);

        if(dietResponse != null){
            return new ResponseEntity<>(dietResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @Transactional(readOnly = true)
    @GetMapping("/pdf/{email}")
    public ResponseEntity<byte[]> downloadDietPdfByEmail(@PathVariable String email) {
        DietPDFDTO dietResponse = dietService.getPdfByEmail(email);

        if (dietResponse == null || dietResponse.getDietPdf() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition
                .builder("attachment")
                .filename("diet_" + email + ".pdf")
                .build());

        return new ResponseEntity<>(dietResponse.getDietPdf(), headers, HttpStatus.OK);
    }

    @GetMapping("/preferences/{email}")
    public ResponseEntity<PreferencesAndAllergiesDTO> getPreferencesAndAllergies(@PathVariable String email){
        PreferencesAndAllergiesDTO preferencesAndAllergiesDTO = dietService.getPreferencesAndAllergies(email);

        if(preferencesAndAllergiesDTO != null)
            return new ResponseEntity<>(preferencesAndAllergiesDTO, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
