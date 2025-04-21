package com.rnyd.rnyd.dto.diet;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DietPDFDTO {

    @JsonProperty("diet_id")
    private Long dietId;

    @JsonProperty("dietPdf")
    private byte[] dietPdf;

    public byte[] getDietPdf() {
        return dietPdf;
    }

    public void setDietPdf(byte[] dietPdf) {
        this.dietPdf = dietPdf;
    }

    // Getters and Setters
    public Long getDietId() {
        return dietId;
    }

    public void setDietId(Long dietId) {
        this.dietId = dietId;
    }

}
