package com.pulawskk.dburger.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class IngredientDto {

    private Long id;

    @Enumerated(EnumType.STRING)
    private String type;
    private String name;

    @JsonProperty(value = "ingredient_url")
    private String url;
}
