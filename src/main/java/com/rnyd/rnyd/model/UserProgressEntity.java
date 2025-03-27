package com.rnyd.rnyd.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_progress")
public class UserProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private String imageUrl;
    private Double weight;
    private Double height;
    private LocalDate progressDate;

    public UserProgressEntity() {}

    public UserProgressEntity(UserEntity user, String imageUrl, Double weight, Double height, LocalDate progressDate) {
        this.user = user;
        this.imageUrl = imageUrl;
        this.weight = weight;
        this.height = height;
        this.progressDate = progressDate;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
