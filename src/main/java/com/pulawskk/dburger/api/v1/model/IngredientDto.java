package com.pulawskk.dburger.api.v1.model;

import lombok.Data;

@Data
public class IngredientDto {

    private Long id;
    private String type;
    private String name;
    private String url;
}
