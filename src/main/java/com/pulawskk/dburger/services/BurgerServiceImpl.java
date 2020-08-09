package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.model.BurgerDto;
import com.pulawskk.dburger.api.v1.model.BurgerListDto;
import com.pulawskk.dburger.repositories.BurgerRepository;
import org.springframework.stereotype.Service;

@Service
public class BurgerServiceImpl implements BurgerService {

    private final BurgerRepository burgerRepository;

    public BurgerServiceImpl(BurgerRepository burgerRepository) {
        this.burgerRepository = burgerRepository;
    }

    @Override
    public BurgerDto findBurgerByName(String name) {
        return null;
    }

    @Override
    public BurgerDto findBurgerById(Long id) {
        return null;
    }

    @Override
    public BurgerListDto findAllBurgers() {
        return null;
    }

    @Override
    public BurgerDto createNewBurger(BurgerDto burgerDto) {
        return null;
    }
}
