package com.pulawskk.dburger.repositories;

import com.pulawskk.dburger.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
