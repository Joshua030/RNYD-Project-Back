package com.rnyd.rnyd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "user_progress")
@Data
public class UserProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "image_url")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

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
