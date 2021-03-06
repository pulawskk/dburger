package com.pulawskk.dburger.controllers.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRestController {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error with processing json", e);
        }
    }

    public static String getUserBaseUrl() {
        return UserController.USER_BASE_URL + "/";
    }

    public static String getOrderBaseUrl() {
        return OrderController.ORDER_BASE_URL + "/";
    }
}
