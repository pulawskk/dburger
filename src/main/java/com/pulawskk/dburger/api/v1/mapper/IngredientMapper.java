package com.pulawskk.dburger.api.v1.mapper;

import com.pulawskk.dburger.api.v1.model.IngredientDto;
import com.pulawskk.dburger.domain.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper {

    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    IngredientDto ingredientToIngredientDto(Ingredient ingredient);

    Ingredient ingredientDtoToIngredient(IngredientDto ingredientDto);
}
