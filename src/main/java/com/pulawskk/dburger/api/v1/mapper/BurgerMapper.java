package com.pulawskk.dburger.api.v1.mapper;

import com.pulawskk.dburger.api.v1.model.BurgerDto;
import com.pulawskk.dburger.domain.Burger;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
@DecoratedWith(BurgerMapperDecorator.class)
public interface BurgerMapper {

    BurgerMapper INSTANCE = Mappers.getMapper(BurgerMapper.class);

    BurgerDto burgerToBurgerDto(Burger burger);

    Burger burgerDtoToBurger(BurgerDto burgerDto);
}
