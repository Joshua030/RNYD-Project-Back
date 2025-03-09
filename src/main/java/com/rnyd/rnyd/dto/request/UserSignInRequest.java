package com.rnyd.rnyd.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class UserSignInRequest {

    @JsonProperty("email")
    private String email;

    @JsonProperty("keyword")
    private String keyword;

    public String getEmail() {
        return email;
    }

    public String getKeyword() {
        return keyword;
    }
}
