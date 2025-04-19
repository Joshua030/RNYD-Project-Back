package com.rnyd.rnyd.dto.diet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietMealDTO {

    @JsonProperty("meal_id")
    private Long mealId;

    @JsonProperty("meal_number")
    private Integer mealNumber;

    @JsonProperty("description")
    private String description;
}
