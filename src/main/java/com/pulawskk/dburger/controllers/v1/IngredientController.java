package com.pulawskk.dburger.controllers.v1;

import com.pulawskk.dburger.api.v1.model.IngredientListDto;
import com.pulawskk.dburger.services.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(IngredientController.INGREDIENT_BASE_URL)
public class IngredientController {

    private final IngredientService ingredientService;

    public static final String INGREDIENT_BASE_URL = "/api/v1/ingredients";

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public IngredientListDto getAllIngredients() {
        return ingredientService.findAllIngredients();
    }
}
