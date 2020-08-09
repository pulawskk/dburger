package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.model.IngredientDto;
import com.pulawskk.dburger.api.v1.model.IngredientListDto;
import com.pulawskk.dburger.domain.Ingredient;
import com.pulawskk.dburger.enums.IngredientType;
import com.pulawskk.dburger.repositories.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @Mock
    IngredientRepository ingredientRepository;

    @InjectMocks
    IngredientServiceImpl ingredientService;

    IngredientDto ingredientDto1, ingredientDto2;

    @BeforeEach
    void setUp() {
        ingredientService = new IngredientServiceImpl(ingredientRepository);

        ingredientDto1 = new IngredientDto();
        ingredientDto1.setType(IngredientType.ROLL.toString());
        ingredientDto1.setName("wheat");
        ingredientDto2 = new IngredientDto();
        ingredientDto2.setType(IngredientType.VEGETABLE.toString());
        ingredientDto2.setName("tomato");

    }

    @Test
    void findIngredientById() {
    }

    @Test
    void findIngredientByName() {
    }

    @Test
    void findIngredientsByType() {
    }

    @Test
    void shouldReturnAllIngredients_whenIngredientsExist() {
        //given
        List<IngredientDto> ingredientsDto = new ArrayList<>();
        ingredientsDto.add(ingredientDto1);
        ingredientsDto.add(ingredientDto2);
        IngredientListDto ingredientListDto = new IngredientListDto(ingredientsDto);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setName("wheat");
        ingredient1.setIngredientType(IngredientType.ROLL);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setName("tomato");
        ingredient2.setIngredientType(IngredientType.VEGETABLE);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        when(ingredientRepository.findAll()).thenReturn(ingredients);

        //when
        IngredientListDto actualIngredientsDto = ingredientService.findAllIngredients();

        //then
        assertAll(() -> {
            assertThat(actualIngredientsDto, notNullValue());
            assertThat(actualIngredientsDto.getIngredients(), hasSize(2));
        });

    }

    @Test
    void addNewIngredient() {
    }

    @Test
    void deleteIngredientById() {
    }
}
