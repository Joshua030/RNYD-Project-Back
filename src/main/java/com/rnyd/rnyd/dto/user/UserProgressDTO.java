package com.rnyd.rnyd.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class UserProgressDTO {

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("weight")
    private Double weight;

    @JsonProperty("height")
    private Double height;

    @JsonProperty("progressDate")
    private LocalDate progressDate;

    // Getters y setters

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public LocalDate getProgressDate() {
        return progressDate;
    }

    public void setProgressDate(LocalDate progressDate) {
        this.progressDate = progressDate;
    }
}
