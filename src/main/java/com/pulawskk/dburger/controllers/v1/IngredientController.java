package com.pulawskk.dburger.controllers.v1;

import com.pulawskk.dburger.api.v1.model.IngredientDto;
import com.pulawskk.dburger.api.v1.model.IngredientListDto;
import com.pulawskk.dburger.services.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{parameter}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientListDto getIngredientsByParameter(@PathVariable String parameter) {
        return ingredientService.findByParameter(parameter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientDto saveNewIngredient(@RequestBody IngredientDto ingredientDto) {
        return ingredientService.addNewIngredient(ingredientDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredientById(id);
    }
}
