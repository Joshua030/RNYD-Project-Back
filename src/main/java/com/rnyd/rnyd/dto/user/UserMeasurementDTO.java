package com.rnyd.rnyd.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class UserMeasurementDTO {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("weight")
    private Double weight;

    @JsonProperty("height")
    private Double height;

    @JsonProperty("neck")
    private Double neck;

    @JsonProperty("shoulders")
    private Double shoulders;

    @JsonProperty("chest")
    private Double chest;

    @JsonProperty("waist")
    private Double waist;

    @JsonProperty("hips")
    private Double hips;

    @JsonProperty("thigh")
    private Double thigh;

    @JsonProperty("calf")
    private Double calf;

    @JsonProperty("main_goal")
    private String mainGoal;

    // Getters y setters (puedes usar Lombok si prefieres)
}
