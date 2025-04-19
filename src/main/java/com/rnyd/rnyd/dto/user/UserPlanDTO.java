package com.rnyd.rnyd.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rnyd.rnyd.utils.constants.Plans;

public class UserPlanDTO {

    @JsonProperty("email")
    private String email;

    @JsonProperty("plan")
    private Plans plan;
}
