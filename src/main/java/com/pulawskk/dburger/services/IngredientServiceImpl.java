package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.mapper.IngredientMapper;
import com.pulawskk.dburger.api.v1.model.IngredientDto;
import com.pulawskk.dburger.api.v1.model.IngredientListDto;
import com.pulawskk.dburger.controllers.v1.IngredientController;
import com.pulawskk.dburger.domain.Ingredient;
import com.pulawskk.dburger.enums.IngredientType;
import com.pulawskk.dburger.exceptions.ResourceNotFoundException;
import com.pulawskk.dburger.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientMapper mapper = IngredientMapper.INSTANCE;

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    private static String getIngredientBaseUrl() {
        return IngredientController.INGREDIENT_BASE_URL + "/";
    }

    @Override
    public IngredientListDto findByParameter(String parameter) {

        try {
            Long id = Long.parseLong(parameter);
            if (id > 0) {
                IngredientDto ingredientDto = findIngredientById(id);
                return new IngredientListDto(ingredientDto);
            }
        } catch (NumberFormatException exception) {
            try {
                IngredientType.valueOf(parameter);
                return findIngredientsByType(parameter);
            } catch (IllegalArgumentException exception1) {

                IngredientDto ingredientDto = findIngredientByName(parameter);
                return new IngredientListDto(ingredientDto);
            }
        }

        throw new ResourceNotFoundException("Could not found any resource for parameter: " + parameter);
    }

    @Override
    public IngredientDto findIngredientById(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        IngredientDto ingredientDto = mapper.ingredientToIngredientDto(ingredient);
        ingredientDto.setUrl(getIngredientBaseUrl() + ingredient.getId());
        return ingredientDto;
    }

    @Override
    public IngredientDto findIngredientByName(String name) {
        Ingredient ingredient = ingredientRepository.findIngredientByName(name).orElseThrow(ResourceNotFoundException::new);
        IngredientDto ingredientDto = mapper.ingredientToIngredientDto(ingredient);
        ingredientDto.setUrl(getIngredientBaseUrl() + ingredient.getId());
        return ingredientDto;
    }

    @Override
    public IngredientListDto findIngredientsByType(String type) {
        IngredientType ingredientType = IngredientType.valueOf(type);
        if (ingredientType != null) {
            return new IngredientListDto(
                    ingredientRepository
                            .findAllByIngredientType(ingredientType)
                            .stream()
                            .map(mapper::ingredientToIngredientDto)
                            .map(dto -> {
                                dto.setUrl(getIngredientBaseUrl() + dto.getId());
                                return dto;
                            })
                            .collect(Collectors.toList()));
        }
        throw new ResourceNotFoundException();
    }

    @Override
    public IngredientListDto findAllIngredients() {
        return new IngredientListDto(ingredientRepository.findAll()
                .stream()
                .map(mapper::ingredientToIngredientDto)
                .map(dto -> {
                    dto.setUrl(getIngredientBaseUrl() + dto.getId());
                    return dto;
                })
                .collect(Collectors.toList()));
    }

    @Override
    public IngredientDto addNewIngredient(IngredientDto ingredientDto) {
        Ingredient ingredientToBeSaved = mapper.ingredientDtoToIngredient(ingredientDto);
        IngredientDto savedIngredientDto = mapper.ingredientToIngredientDto(ingredientRepository.save(ingredientToBeSaved));
        savedIngredientDto.setUrl(getIngredientBaseUrl() + savedIngredientDto.getId());
        return savedIngredientDto;
    }

    @Override
    public void deleteIngredientById(Long id) {
        ingredientRepository.deleteById(id);
    }
}
