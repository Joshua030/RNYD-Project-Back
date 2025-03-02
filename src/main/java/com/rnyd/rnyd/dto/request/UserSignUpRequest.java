package com.rnyd.rnyd.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class UserSignUpRequest {

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

    public String getEmail() {
        return email;
    }
}
