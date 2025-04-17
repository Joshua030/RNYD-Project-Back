package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.DietDTO;

import java.util.List;

public interface DietUseCase {

    List<DietDTO> getAllDiets();

     DietDTO getDietById(Long id);

 String updateDiet(DietDTO dietDTO);

 String createDiet(DietDTO dietDTO);

 String assignDiet(String email, DietDTO dietDTO);

 String deleteDiet(Long id);
}
