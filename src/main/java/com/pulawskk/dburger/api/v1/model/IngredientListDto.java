package com.pulawskk.dburger.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class IngredientListDto {
    List<IngredientDto> ingredients;
}
