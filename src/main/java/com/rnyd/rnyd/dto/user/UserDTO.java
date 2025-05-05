package com.rnyd.rnyd.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rnyd.rnyd.dto.workout.WorkOutDTO;
import com.rnyd.rnyd.dto.diet.DietDTO;
import com.rnyd.rnyd.utils.constants.Roles;
import com.rnyd.rnyd.utils.constants.Gender;
import com.rnyd.rnyd.utils.constants.GymGoal;

import java.time.LocalDate;
import java.util.List;

public class UserDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("birth_date")
    private LocalDate birth_date;

    @JsonProperty("keyword")
    private String keyword;

    @JsonProperty("role")
    private Roles role;

    @JsonProperty("subscription_product_id")
    private String subscriptionProductId;

    @JsonProperty("subscription_name")
    private String subscriptionName;

    @JsonProperty("subscription_amount")
    private Long subscriptionAmount;

    @JsonProperty("userProgress")
    private List<UserProgressDTO> progressList;

    @JsonProperty("measurements")
    private UserMeasurementDTO measurements;

    @JsonProperty("meals_per_day")
    private Integer mealsPerDay;

    @JsonProperty("allergies")
    private String allergies;

    @JsonProperty("injuries")
    private String injuries;

    @JsonProperty("gym_goal")
    private GymGoal gymGoal;

    @JsonProperty("training_days")
    private String trainingDays;

    @JsonProperty("diets")
    private List<DietDTO> diets;

    @JsonProperty("workouts")
    private List<WorkOutDTO> workouts;

    @JsonProperty("gender")
    private Gender gender;

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

    public UserDTO(String name, String surname, String email, String keyword, LocalDate birth_date, Roles role,
            String subscriptionProductId) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.keyword = keyword;
        this.birth_date = birth_date;
        this.role = role;
        this.subscriptionProductId = subscriptionProductId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
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

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public Long getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public void setSubscriptionAmount(Long subscriptionAmount) {
        this.subscriptionAmount = subscriptionAmount;
    }

    public List<UserProgressDTO> getProgressList() {
        return progressList;
    }

    public void setProgressList(List<UserProgressDTO> progressList) {
        this.progressList = progressList;
    }

    public UserMeasurementDTO getMeasurements() {
        return measurements;
    }

    public void setMeasurements(UserMeasurementDTO measurements) {
        this.measurements = measurements;
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

    public List<DietDTO> getDiets() {
        return diets;
    }

    public void setDiets(List<DietDTO> diets) {
        this.diets = diets;
    }

    public List<WorkOutDTO> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<WorkOutDTO> workouts) {
        this.workouts = workouts;
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
