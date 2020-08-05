package com.pulawskk.dburger.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZIP;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
    private LocalDateTime placedAt;

    @JsonProperty(value = "order_url")
    private String orderUrl;

    private Long userId;
}
