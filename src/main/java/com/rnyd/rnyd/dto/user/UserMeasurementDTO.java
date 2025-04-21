package com.rnyd.rnyd.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class UserMeasurementDTO {

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Double getNeck() {
        return neck;
    }

    public void setNeck(Double neck) {
        this.neck = neck;
    }

    public Double getShoulders() {
        return shoulders;
    }

    public void setShoulders(Double shoulders) {
        this.shoulders = shoulders;
    }

    public Double getChest() {
        return chest;
    }

    public void setChest(Double chest) {
        this.chest = chest;
    }

    public Double getWaist() {
        return waist;
    }

    public void setWaist(Double waist) {
        this.waist = waist;
    }

    public Double getHips() {
        return hips;
    }

    public void setHips(Double hips) {
        this.hips = hips;
    }

    public Double getThigh() {
        return thigh;
    }

    public void setThigh(Double thigh) {
        this.thigh = thigh;
    }

    public Double getCalf() {
        return calf;
    }

    public void setCalf(Double calf) {
        this.calf = calf;
    }

    public String getMainGoal() {
        return mainGoal;
    }

    public void setMainGoal(String mainGoal) {
        this.mainGoal = mainGoal;
    }

    // Getters y setters (puedes usar Lombok si prefieres)
}
