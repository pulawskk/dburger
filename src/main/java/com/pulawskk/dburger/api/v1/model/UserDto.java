package com.pulawskk.dburger.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDto {
    Long id;
    private String firstName;
    private String lastName;
    private String email;

    @JsonProperty(value = "user_url")
    private String userUrl;
}
