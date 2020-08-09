package com.pulawskk.dburger.api.v1.model;

import com.pulawskk.dburger.domain.Burger;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BurgerListDto {
    List<BurgerDto> burgers;
}
