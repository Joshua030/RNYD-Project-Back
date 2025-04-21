package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.diet.DietDTO;
import com.rnyd.rnyd.dto.diet.DietPDFDTO;
import com.rnyd.rnyd.dto.diet.PreferencesAndAllergiesDTO;

import java.util.List;

public interface DietUseCase {

 List<DietDTO> getAllDiets();

     DietDTO getDietByEmail(String email);

     String updateDiet(DietDTO dietDTO);
    String updateDietWithPdf(DietPDFDTO dietDTO);

     String createDiet(DietDTO dietDTO);
    String createDietWithPdf(DietPDFDTO dietDTO);

     String assignDiet(String email, DietDTO dietDTO);

     String deleteDiet(Long id);

     PreferencesAndAllergiesDTO getPreferencesAndAllergies(String email);
}
