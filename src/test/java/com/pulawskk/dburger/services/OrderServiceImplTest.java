package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.model.OrderListDto;
import com.pulawskk.dburger.domain.Order;
import com.pulawskk.dburger.domain.User;
import com.pulawskk.dburger.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    private User user;
    private Order order1, order2;
    private List<Order> orders = new ArrayList<>();

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl(orderRepository);

        user = new User();
        user.setFirstName("name");

        order1 = new Order();
        order2 = new Order();

        order1.setId(11L);
        order1.setUser(user);
        order1.setDeliveryName("delivery 1");

        order2.setId(22L);
        order2.setUser(user);
        order2.setDeliveryName("delivery 2");

        orders.add(order1);
        orders.add(order2);
    }

    @Test
    void shouldReturnAllOrders_whenOrdersExist() {
        //given
        when(orderRepository.findAll()).thenReturn(orders);


        //when
        OrderListDto actualOrders = orderService.findAllOrdersDto();

        //then
        assertAll(() -> {
            assertThat(actualOrders, notNullValue());
            assertThat(actualOrders.getOrders().size(), is(2));
        });

    }

    @Test
    void findOrderById() {
    }

    @Test
    void createNewOrder() {
    }

    @Test
    void updateOrder() {
    }

    @Test
    void patchOrder() {
    }

    @Test
    void deleteOrder() {
    }
}
