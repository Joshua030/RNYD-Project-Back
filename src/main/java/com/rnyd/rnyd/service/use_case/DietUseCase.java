package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.diet.DietDTO;
import com.rnyd.rnyd.dto.diet.DietPDFDTO;
import com.rnyd.rnyd.dto.diet.PreferencesAndAllergiesDTO;

import java.util.List;

public interface DietUseCase {

 List<DietDTO> getAllDiets();

     DietDTO getDietByEmail(String email);

     String updateDiet(String email, DietDTO dietDTO);
    String updateDietWithPdf(String email, DietPDFDTO dietDTO);

     String createDiet(DietDTO dietDTO);
    String createDietWithPdf(DietPDFDTO dietDTO);

     String assignDiet(String email, DietDTO dietDTO);

     String deleteDiet(String id);

     PreferencesAndAllergiesDTO getPreferencesAndAllergies(String email);
}
