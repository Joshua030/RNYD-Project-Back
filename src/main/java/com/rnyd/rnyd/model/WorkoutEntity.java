package com.rnyd.rnyd.model;


import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_workouts")
public class WorkoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_id")
    private Long workoutId;

    @Column(name = "workout_name", length = 100)
    private String workoutName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "workout_pdf")
    private byte[] workoutPdf;

    public String getWorkoutUrl() {
        return workoutUrl;
    }

    public void setWorkoutUrl(String workoutUrl) {
        this.workoutUrl = workoutUrl;
    }

    @Column(name = "workout_url")
    private String workoutUrl;

    public byte[] getWorkoutPdf() {
        return workoutPdf;
    }

    public void setWorkoutPdf(byte[] workoutPdf) {
        this.workoutPdf = workoutPdf;
    }

    public WorkoutEntity() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
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