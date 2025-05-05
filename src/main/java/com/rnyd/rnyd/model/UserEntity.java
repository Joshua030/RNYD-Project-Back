package com.rnyd.rnyd.model;

import com.rnyd.rnyd.utils.constants.Gender;
import com.rnyd.rnyd.utils.constants.GymGoal;
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

    // ✅ New field to store Stripe product ID (instead of old 'plan' enum)
    @Column(name = "subscription_product_id")
    private String subscriptionProductId;

    @Column(name = "meals_per_day")
    private Integer mealsPerDay;

    @Column(name = "allergies")
    private String allergies;

    @Column(name = "injuries")
    private String injuries;

    @Enumerated(EnumType.STRING)
    @Column(name = "gym_goal")
    private GymGoal gymGoal;

    @Column(name = "training_days")
    private String trainingDays;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height")
    private Double height;

    @Column(name = "neck")
    private Double neck;

    @Column(name = "shoulders")
    private Double shoulders;

    @Column(name = "chest")
    private Double chest;

    @Column(name = "waist")
    private Double waist;

    @Column(name = "hips")
    private Double hips;

    @Column(name = "thigh")
    private Double thigh;

    @Column(name = "calf")
    private Double calf;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DietEntity> diets;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WorkoutEntity> workouts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserProgressEntity> progressList = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserMeasurementEntity measurements;

    // 🔧 Constructors

    public UserEntity() {
    }

    public UserEntity(Long id, String email, String name, String surname, LocalDate birth_date, String keyword,
            Roles role, String subscriptionProductId) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.keyword = keyword;
        this.role = role;
        this.subscriptionProductId = subscriptionProductId;
    }

    // 🔧 Getters and Setters

    public Long getId() {
        return id;
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

    public String getSubscriptionProductId() {
        return subscriptionProductId;
    }

    public void setSubscriptionProductId(String subscriptionProductId) {
        this.subscriptionProductId = subscriptionProductId;
    }

    public Integer getMealsPerDay() {
        return mealsPerDay;
    }

    public void setMealsPerDay(Integer mealsPerDay) {
        this.mealsPerDay = mealsPerDay;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getInjuries() {
        return injuries;
    }

    public void setInjuries(String injuries) {
        this.injuries = injuries;
    }

    public GymGoal getGymGoal() {
        return gymGoal;
    }

    public void setGymGoal(GymGoal gymGoal) {
        this.gymGoal = gymGoal;
    }

    public String getTrainingDays() {
        return trainingDays;
    }

    public void setTrainingDays(String trainingDays) {
        this.trainingDays = trainingDays;
    }

    public List<DietEntity> getDiets() {
        return diets;
    }

    public void setDiets(List<DietEntity> diets) {
        this.diets = diets;
    }

    public List<WorkoutEntity> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<WorkoutEntity> workouts) {
        this.workouts = workouts;
    }

    public List<UserProgressEntity> getProgressList() {
        return progressList;
    }

    public void setProgressList(List<UserProgressEntity> progressList) {
        this.progressList = progressList;
    }

    public UserMeasurementEntity getMeasurements() {
        return measurements;
    }

    public void setMeasurements(UserMeasurementEntity measurements) {
        this.measurements = measurements;
    }

    public Gender getGender() {
        return gender;
    }
    
    public void setGender(Gender gender) {
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
    
}
