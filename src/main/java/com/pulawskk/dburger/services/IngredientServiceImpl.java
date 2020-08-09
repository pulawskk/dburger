package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.mapper.IngredientMapper;
import com.pulawskk.dburger.api.v1.model.IngredientDto;
import com.pulawskk.dburger.api.v1.model.IngredientListDto;
import com.pulawskk.dburger.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientMapper mapper = IngredientMapper.INSTANCE;

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientDto findIngredientById(Long id) {
        return null;
    }

    @Override
    public IngredientDto findIngredientByName(Long name) {
        return null;
    }

    @Override
    public IngredientListDto findIngredientsByType(String type) {
        return null;
    }

    @Override
    public IngredientListDto findAllIngredients() {
        return new IngredientListDto(ingredientRepository.findAll()
                .stream()
                .map(mapper::ingredientToIngredientDto).collect(Collectors.toList()));
    }

    @Override
    public IngredientDto addNewIngredient(IngredientDto ingredientDto) {
        return null;
    }

    @Override
    public void deleteIngredientById(Long id) {

    }
}
