package com.rnyd.rnyd.service.use_case;

import com.rnyd.rnyd.dto.DietDTO;

public interface DietUseCase {

    public DietDTO getDietByUserEmail(String email);

    public DietDTO updateDiet(DietDTO dietDTO);

    public DietDTO createDiet(DietDTO dietDTO);

    public DietDTO assignDiet(String email, DietDTO dietDTO);

    public DietDTO deleteDiet(Long id);
}
