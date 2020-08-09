package com.pulawskk.dburger.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BurgerDto {
    private Long id;
    private String name;
    private String price;

    @JsonProperty(value = "order_url")
    private String order_url;
}
