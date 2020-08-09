package com.pulawskk.dburger.api.v1.mapper;

import com.pulawskk.dburger.api.v1.model.BurgerDto;
import com.pulawskk.dburger.domain.Burger;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class BurgerMapperTest {

    public static final long ID = 11L;
    public static final String NAME = "test name";
    public static final String PRICE = "11.11";
    private final BurgerMapper mapper = BurgerMapper.INSTANCE;

    @Test
    void shouldConvertBurgerToBurgerDto_whenBurgerIsGiven() {
        //given
        Burger burger = new Burger();
        burger.setId(ID);
        burger.setName(NAME);
        burger.setPrice(new BigDecimal(PRICE));

        //when
        BurgerDto actualBurgerDto = mapper.burgerToBurgerDto(burger);

        //then
        assertAll(() -> {
            assertThat(actualBurgerDto, notNullValue());
            assertThat(actualBurgerDto.getId(), is(ID));
            assertThat(actualBurgerDto.getName(), is(NAME));
            assertThat(actualBurgerDto.getPrice(), is(PRICE));
        });
    }

    @Test
    void shouldConvertBurgerDtoToBurger() {
        //given
        BurgerDto burgerDto = new BurgerDto();
        burgerDto.setId(ID);
        burgerDto.setName(NAME);
        burgerDto.setPrice(PRICE);

        //when
        Burger actualBurger = mapper.burgerDtoToBurger(burgerDto);

        //then
        assertAll(() -> {
            assertThat(actualBurger, notNullValue());
            assertThat(actualBurger.getId(), is(ID));
            assertThat(actualBurger.getName(), is(NAME));
            assertThat(actualBurger.getPrice(), is(new BigDecimal(PRICE)));
        });
    }
}
