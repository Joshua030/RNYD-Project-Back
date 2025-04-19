package com.rnyd.rnyd.dto.diet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietDayDTO {

    @JsonProperty("day_id")
    private Long dayId;

    @JsonProperty("day_of_week")
    private String dayOfWeek;

    @JsonProperty("meals")
    private List<DietMealDTO> meals;
}
