package com.rnyd.rnyd.dto.workout;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WorkOutDTO {

    @JsonProperty("workout_id")
    private Long workoutId;

    @JsonProperty("workout_name")
    private String workoutName;

    @JsonProperty("start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonProperty("note")
    private String note;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty("workout_url")
    private String workoutUrl;

    @JsonProperty("user_email")
private String userEmail;

@JsonProperty("user_name")
private String userName;

    public String getWorkoutUrl() {
        return workoutUrl;
    }

    public void setWorkoutUrl(String workoutUrl) {
        this.workoutUrl = workoutUrl;
    }

    // Getters and Setters
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

    public String getUserEmail() {
        return userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
