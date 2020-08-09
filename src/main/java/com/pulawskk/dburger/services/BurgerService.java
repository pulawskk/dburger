package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.model.BurgerDto;
import com.pulawskk.dburger.api.v1.model.BurgerListDto;

public interface BurgerService {

    BurgerDto findBurgerByName(String name);

    BurgerDto findBurgerById(Long id);

    BurgerListDto findAllBurgers();

    BurgerDto createNewBurger(BurgerDto burgerDto);
}
