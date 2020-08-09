package com.pulawskk.dburger.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class IngredientListDto {
    List<IngredientDto> ingredients;

    public void addIngredientDto(IngredientDto ingredientDto) {
        if (this.ingredients == null) {
            this.ingredients = new ArrayList<>();
        }

        this.ingredients.add(ingredientDto);
    }

    public IngredientListDto(IngredientDto ingredientDto) {
        addIngredientDto(ingredientDto);
    }
}
