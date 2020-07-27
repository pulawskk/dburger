package com.pulawskk.dburger.api.v1.model;

import lombok.Data;

@Data
public class UserDto {
    Long id;
    private String firstName;
    private String lastName;
    private String email;
}
