package com.pulawskk.dburger.controllers.v1;

import com.pulawskk.dburger.api.v1.model.BurgerDto;
import com.pulawskk.dburger.api.v1.model.BurgerListDto;
import com.pulawskk.dburger.services.BurgerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BurgerController.BURGER_BASE_URL)
@CrossOrigin
public class BurgerController {

    public static final String BURGER_BASE_URL = "/api/v1/burgers";

    private final BurgerService burgerService;

    public BurgerController(BurgerService burgerService) {
        this.burgerService = burgerService;
    }

    @RequestMapping
    @ResponseStatus(HttpStatus.OK)
    public BurgerListDto getAllBurgers() {
        return burgerService.findAllBurgers();
    }

    @RequestMapping("{parameter}")
    public BurgerDto getBurgerByParameter(@PathVariable String parameter) {
        return burgerService.findBurgerByParameter(parameter);
    }
}
