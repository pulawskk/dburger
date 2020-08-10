package com.pulawskk.dburger.api.v1.mapper;

import com.pulawskk.dburger.api.v1.model.BurgerDto;
import com.pulawskk.dburger.domain.Burger;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BurgerMapperDecorator implements BurgerMapper {

    private BurgerMapper burgerMapper;

    @Autowired
    public void setBurgerMapper(BurgerMapper burgerMapper) {
        this.burgerMapper = burgerMapper;
    }

    @Override
    public BurgerDto burgerToBurgerDto(Burger burger) {
        BurgerDto burgerDto = burgerMapper.burgerToBurgerDto(burger);
        burgerDto.setIngredientsJson(burger.toStringIngredients());
        return burgerDto;
    }

    @Override
    public Burger burgerDtoToBurger(BurgerDto burgerDto) {
        return burgerMapper.burgerDtoToBurger(burgerDto);
    }
}
