package com.rnyd.rnyd.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class UserProgressDTO {

    @JsonProperty("imageFile")
    private byte[] imageFile;

    @JsonProperty("imagePath")
    private String imagePath;

    @JsonProperty("weight")
    private Double weight;

    @JsonProperty("height")
    private Double height;

    @JsonProperty("progressDate")
    private LocalDate progressDate;

    // Getters y setters

    public byte[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
