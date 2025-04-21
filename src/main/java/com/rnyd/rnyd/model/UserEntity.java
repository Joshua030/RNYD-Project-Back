package com.rnyd.rnyd.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rnyd.rnyd.utils.constants.Plans;
import com.rnyd.rnyd.utils.constants.Roles;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"user\"")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "surname", length = 75, nullable = false)
    private String surname;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birth_date;

    @Column(name = "keyword", length = 100, nullable = false)
    private String keyword;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Roles role;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan")
    private Plans plan;

    @OneToOne(optional = true)
    @JoinColumn(name = "workout_id", referencedColumnName = "workout_id")
    private WorkoutEntity workout;

    @OneToOne(optional = true, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "diet_id")
    private DietEntity diet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserProgressEntity> progressList = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserMeasurementEntity measurements;

    public UserMeasurementEntity getMeasurements() {
        return measurements;
    }

    public void setMeasurements(UserMeasurementEntity measurements) {
        this.measurements = measurements;
    }

    public UserEntity() {
    }

    public UserEntity(Long id, String email, String name, String surname, LocalDate birth_date, String keyword, Roles role, Plans plan) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.keyword = keyword;
        this.role = role;
        this.plan = plan;
    }

    public Long getId() {
        return id;
    }

    public List<UserProgressEntity> getProgressList() {
        return progressList;
    }

    public void setProgressList(List<UserProgressEntity> progressList) {
        this.progressList = progressList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Plans getPlan() {
        return plan;
    }

    public void setPlan(Plans plan) {
        this.plan = plan;
    }

    public WorkoutEntity getWorkout() {
        return workout;
    }

    public void setWorkout(WorkoutEntity workout) {
        this.workout = workout;
    }

    public DietEntity getDiet() {
        return diet;
    }

    public void setDiet(DietEntity diet) {
        this.diet = diet;
    }
}
