package com.rnyd.rnyd.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "diet_days")
public class DietDayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_id")
    private Long dayId;

    @ManyToOne
    @JoinColumn(name = "diet_id")
    private DietEntity userDiet;

    @Column(name = "day_of_week")
    private String dayOfWeek;

    @OneToMany(mappedBy = "dietDay", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DietMealEntity> meals;

    public DietDayEntity() {
    }

    public DietDayEntity(Long dayId, DietEntity userDiet, String dayOfWeek, List<DietMealEntity> meals) {
        this.dayId = dayId;
        this.userDiet = userDiet;
        this.dayOfWeek = dayOfWeek;
        this.meals = meals;
    }

    public Long getDayId() {
        return dayId;
    }

    public void setDayId(Long dayId) {
        this.dayId = dayId;
    }

    public DietEntity getUserDiet() {
        return userDiet;
    }

    public void setUserDiet(DietEntity userDiet) {
        this.userDiet = userDiet;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<DietMealEntity> getMeals() {
        return meals;
    }

    public void setMeals(List<DietMealEntity> meals) {
        this.meals = meals;
    }
}
