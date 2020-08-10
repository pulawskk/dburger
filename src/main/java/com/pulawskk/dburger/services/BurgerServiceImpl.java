package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.mapper.BurgerMapper;
import com.pulawskk.dburger.api.v1.model.BurgerDto;
import com.pulawskk.dburger.api.v1.model.BurgerListDto;
import com.pulawskk.dburger.controllers.v1.BurgerController;
import com.pulawskk.dburger.domain.Burger;
import com.pulawskk.dburger.exceptions.ResourceNotFoundException;
import com.pulawskk.dburger.repositories.BurgerRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BurgerServiceImpl implements BurgerService {

    private final BurgerMapper mapper;

    private final BurgerRepository burgerRepository;

    public BurgerServiceImpl(BurgerMapper mapper, BurgerRepository burgerRepository) {
        this.mapper = mapper;
        this.burgerRepository = burgerRepository;
    }

    /**
     * Return base url for burgers api
     * @return url
     */
    private String getBurgerBaseUrl() {
        return BurgerController.BURGER_BASE_URL + "/";
    }

    @Override
    public BurgerDto findBurgerByParameter(String parameter) {
        try {
            Long id = Long.parseLong(parameter);
            if (id > 0) {
                return findBurgerById(id);
            }
        } catch (NumberFormatException exception) {
            return findBurgerByName(parameter);
        }
        throw new ResourceNotFoundException("Not found burger with parameter: " + parameter);
    }

    @Override
    public BurgerDto findBurgerByName(String name) {
        BurgerDto burgerDto = mapper.burgerToBurgerDto(burgerRepository.findBurgerByName(name).orElseThrow(ResourceNotFoundException::new));
        burgerDto.setOrderUrl(getBurgerBaseUrl() + burgerDto.getId());
        return burgerDto;
    }

    @Override
    public BurgerDto findBurgerById(Long id) {
        BurgerDto burgerDto = mapper.burgerToBurgerDto(burgerRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
        burgerDto.setOrderUrl(getBurgerBaseUrl() + burgerDto.getId());
        return burgerDto;
    }

    @Override
    public BurgerListDto findAllBurgers() {
        return new BurgerListDto(burgerRepository.findAll()
                .stream()
                .map(mapper::burgerToBurgerDto)
                .map(dto -> {
                    dto.setOrderUrl(getBurgerBaseUrl() + dto.getId());
                    return dto;
                })
                .collect(Collectors.toList()));
    }

    @Override
    public BurgerDto createNewBurger(BurgerDto burgerDto) {
        Burger savedBurger = burgerRepository.save(mapper.burgerDtoToBurger(burgerDto));
        BurgerDto savedBurgerDto = mapper.burgerToBurgerDto(savedBurger);
        savedBurgerDto.setOrderUrl(getBurgerBaseUrl() + savedBurger.getId());
        return savedBurgerDto;
    }

    @Override
    public void deleteBurgerById(Long id) {
        burgerRepository.deleteById(id);
    }

}
