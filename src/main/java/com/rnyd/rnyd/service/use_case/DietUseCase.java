package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.diet.DietDTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface DietUseCase {

    List<DietDTO> getAllDietsWithUsers();

    List<DietDTO> getDietsWithoutUser();

    List<DietDTO> getDietsByEmailWithUser(String email);

    List<DietDTO> getDietsByEmail(String email);

    DietDTO getDietById(Long id);

    String createDiet(DietDTO dietDTO, MultipartFile dietPdfFile);

    String assignDiet(String email, DietDTO dietDTO);
    boolean deleteDietById(Long id);

}
