package com.pulawskk.dburger.api.v1.mapper;

import com.pulawskk.dburger.api.v1.model.IngredientDto;
import com.pulawskk.dburger.domain.Ingredient;
import com.pulawskk.dburger.enums.IngredientType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

class IngredientMapperTest {

    private static final Long ID = 11L;
    private static final String NAME = "tomato";
    private static final String TYPE = "VEGETABLE";
    private static final IngredientType VEGETABLE = IngredientType.VEGETABLE;

    private final IngredientMapper mapper = IngredientMapper.INSTANCE;

    @Test
    void shouldConvertIngredientToIngredientDto_whenIngredientIsGiven() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setName(NAME);
        ingredient.setIngredientType(VEGETABLE);

        //when
        IngredientDto actualIngredientDto = mapper.ingredientToIngredientDto(ingredient);

        //then
        assertAll(() -> {
            assertThat(actualIngredientDto, notNullValue());
            assertThat(actualIngredientDto.getId(), is(ID));
            assertThat(actualIngredientDto.getName(), is(NAME));
            assertThat(actualIngredientDto.getType(), is(TYPE));
        });
    }

    @Test
    void shouldConvertIngredientDtoToIngredient_whenIngredientDtoIsGiven() {
        //given
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(ID);
        ingredientDto.setName(NAME);
        ingredientDto.setType(TYPE);

        //when
        Ingredient actualIngredient = mapper.ingredientDtoToIngredient(ingredientDto);

        //then
        assertAll(() -> {
            assertThat(actualIngredient, notNullValue());
            assertThat(actualIngredient.getId(), is(ID));
            assertThat(actualIngredient.getName(), is(NAME));
            assertThat(actualIngredient.getIngredientType(), is(VEGETABLE));
        });
    }
}
