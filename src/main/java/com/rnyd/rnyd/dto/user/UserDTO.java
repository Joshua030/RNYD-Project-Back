package com.rnyd.rnyd.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rnyd.rnyd.dto.workout.WorkOutDTO;
import com.rnyd.rnyd.dto.diet.DietDTO;
import com.rnyd.rnyd.utils.constants.Plans;
import com.rnyd.rnyd.utils.constants.Roles;

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

    @JsonProperty(value = "plan")
    private Plans plan;

    @JsonProperty("userProgress")
    private List<UserProgressDTO> progressList;

    @JsonProperty(value = "measurements")
    private UserMeasurementDTO measurements;

    public Plans getPlan() {
        return plan;
    }

    public void setPlan(Plans plan) {
        this.plan = plan;
    }

    public List<UserProgressDTO> getProgressList() {
        return progressList;
    }

    public void setProgressList(List<UserProgressDTO> progressList) {
        this.progressList = progressList;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getKeyword() {
        return keyword;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public UserDTO(String name, String surname, String email, String keyword, LocalDate birth_date, Roles role, Plans plan) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.keyword = keyword;
        this.birth_date = birth_date;
        this.role = role;
        this.plan = plan;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public UserMeasurementDTO getMeasurements() {
        return measurements;
    }

    public void setMeasurements(UserMeasurementDTO measurements) {
        this.measurements = measurements;
    }
}
