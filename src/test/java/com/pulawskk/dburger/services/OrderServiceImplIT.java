package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.mapper.BurgerMapper;
import com.pulawskk.dburger.api.v1.mapper.BurgerMapperDecorator;
import com.pulawskk.dburger.api.v1.model.OrderDto;
import com.pulawskk.dburger.bootstrap.BootstrapData;
import com.pulawskk.dburger.domain.Order;
import com.pulawskk.dburger.repositories.BurgerRepository;
import com.pulawskk.dburger.repositories.IngredientRepository;
import com.pulawskk.dburger.repositories.OrderRepository;
import com.pulawskk.dburger.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class OrderServiceImplIT {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    BurgerRepository burgerRepository;

    @Autowired
    BurgerService burgerService;

    OrderServiceImpl orderService;
    BurgerMapper burgerMapper = BurgerMapper.INSTANCE;

    @BeforeEach
    void setUp() throws Exception {
        BootstrapData bootstrapData = new BootstrapData(userRepository, orderRepository, ingredientRepository, burgerRepository);
        bootstrapData.run();

        orderService = new OrderServiceImpl(orderRepository, burgerService, burgerMapper);
    }

    /**
     * Helper method to get id for latest added user
     * @return id for User object
     */
    private Long getLatestUserIdFromDb() {
        return orderRepository.findAll().get(0).getId();
    }

    @Test
    void shouldPatchOrderDeliveryName_whenOnlyDeliveryNameIsGiven() {
        //given
        Long id = getLatestUserIdFromDb();
        String deliveryNameToUpdate = "Update delivery name test";

        Order originalOrder = orderRepository.getOne(id);
        assertNotNull(originalOrder);
        String originalDeliveryName = originalOrder.getDeliveryName();
        String originalDeliveryCity = originalOrder.getDeliveryCity();

        OrderDto orderDto = new OrderDto();
        orderDto.setDeliveryName(deliveryNameToUpdate);

        //when
        orderService.patchOrder(id, orderDto);
        Order patchedOrder = orderRepository.findById(id).orElseGet(Order::new);

        //then
        assertAll(() -> {
            assertThat(patchedOrder, notNullValue());
            assertThat(patchedOrder.getDeliveryName(), is(deliveryNameToUpdate));
            assertThat(patchedOrder.getDeliveryName(), not(originalDeliveryName));
            assertThat(patchedOrder.getDeliveryCity(), is(originalDeliveryCity));
        });
    }
}
