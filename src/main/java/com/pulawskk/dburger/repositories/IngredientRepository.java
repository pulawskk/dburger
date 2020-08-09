package com.pulawskk.dburger.repositories;

import com.pulawskk.dburger.api.v1.model.IngredientDto;
import com.pulawskk.dburger.api.v1.model.IngredientListDto;
import com.pulawskk.dburger.domain.Ingredient;
import com.pulawskk.dburger.enums.IngredientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findAllByIngredientType(IngredientType ingredientType);

    Optional<Ingredient> findIngredientByName(String name);
}
