package com.rnyd.rnyd.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "diet_meals")
public class DietMealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Long mealId;

    @ManyToOne
    @JoinColumn(name = "day_id", nullable = false)
    private DietDayEntity dietDay;

    @Column(name = "meal_number")
    private Integer mealNumber;

    @Column(name = "description")
    private String description;

    public DietMealEntity() {
    }

    public DietMealEntity(Long mealId, DietDayEntity dietDay, Integer mealNumber, String description) {
        this.mealId = mealId;
        this.dietDay = dietDay;
        this.mealNumber = mealNumber;
        this.description = description;
    }

    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public DietDayEntity getDietDay() {
        return dietDay;
    }

    public void setDietDay(DietDayEntity dietDay) {
        this.dietDay = dietDay;
    }

    public Integer getMealNumber() {
        return mealNumber;
    }

    public void setMealNumber(Integer mealNumber) {
        this.mealNumber = mealNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
