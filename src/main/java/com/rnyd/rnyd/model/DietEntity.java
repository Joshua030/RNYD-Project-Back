package com.rnyd.rnyd.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_diets")
public class DietEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_id")
    private Long dietId;

    @Column(name = "diet_name", length = 100)
    private String dietName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(columnDefinition = "TEXT")
    private String note;

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "preferences", columnDefinition = "TEXT")
    private String preferences;

    @Column(name = "allergies", columnDefinition = "TEXT")
    private String allergies;

    @OneToMany(mappedBy = "userDiet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DietDayEntity> days;

    public List<DietDayEntity> getDays() {
        return days;
    }

    public void setDays(List<DietDayEntity> days) {
        this.days = days;
    }

    public Long getDietId() {
        return dietId;
    }

    public void setDietId(Long dietId) {
        this.dietId = dietId;
    }

    public String getDietName() {
        return dietName;
    }

    public void setDietName(String dietName) {
        this.dietName = dietName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
