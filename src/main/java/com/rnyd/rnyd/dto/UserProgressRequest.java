package com.rnyd.rnyd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class UserProgressRequest {

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("weight")
    private Double weight;

    @JsonProperty("height")
    private Double height;

    @JsonProperty("progressDate")
    private LocalDate progressDate;

    public String getImageUrl() {
        return imageUrl;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getHeight() {
        return height;
    }

    public LocalDate getProgressDate() {
        return progressDate;
    }
}
