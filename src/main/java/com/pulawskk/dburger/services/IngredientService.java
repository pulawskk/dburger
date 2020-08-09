package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.model.IngredientDto;
import com.pulawskk.dburger.api.v1.model.IngredientListDto;

public interface IngredientService {

    IngredientDto findIngredientById(Long id);

    IngredientDto findIngredientByName(Long name);

    IngredientListDto findIngredientsByType(String type);

    IngredientListDto findAllIngredients();

    IngredientDto addNewIngredient(IngredientDto ingredientDto);

    void deleteIngredientById(Long id);
}
