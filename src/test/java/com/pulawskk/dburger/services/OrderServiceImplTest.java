package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.model.OrderDto;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    public static final long ID_11 = 11L;
    public static final long ID_22 = 22L;
    public static final String DELIVERY_NAME = "delivery test";
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

        order1.setId(ID_11);
        order1.setUser(user);
        order1.setDeliveryName(DELIVERY_NAME);
        order1.setPlacedAt(LocalDateTime.now());

        order2.setId(ID_22);
        order2.setUser(user);
        order2.setDeliveryName(DELIVERY_NAME);
        order2.setPlacedAt(LocalDateTime.now());

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
            assertThat(actualOrders.getOrders().get(0).getOrderUrl().isBlank(), is(false));
            assertThat(actualOrders.getOrders().get(0).getPlacedAt(), notNullValue());
        });
    }

    @Test
    void shouldReturnOrder_whenOrderExistsForSpecificId() {
        //given
        when(orderRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(order1));

        //when
        OrderDto actualOrderDto = orderService.findOrderById(anyLong());

        //then
        assertAll(() -> {
            assertThat(actualOrderDto, notNullValue());
            assertThat(actualOrderDto.getId(), is(ID_11));
            assertThat(actualOrderDto.getDeliveryName(), is(DELIVERY_NAME));
            assertThat(actualOrderDto.getOrderUrl(), is("/api/v1/orders/" + ID_11));
            assertThat(actualOrderDto.getPlacedAt(), notNullValue());
        });
    }

    @Test
    void shouldCreateNewOrder_whenOrderDtoIsGiven() {
        //given
        OrderDto orderDto = new OrderDto();
        orderDto.setDeliveryName(DELIVERY_NAME);

        when(orderRepository.save(any(Order.class))).thenReturn(order1);

        //when
        OrderDto createdOrderDto = orderService.createNewOrder(orderDto);

        //then
        assertAll(() -> {
            assertThat(createdOrderDto, notNullValue());
            assertThat(createdOrderDto.getId(), is(ID_11));
            assertThat(createdOrderDto.getDeliveryName(), is(DELIVERY_NAME));
            assertThat(createdOrderDto.getOrderUrl(), is("/api/v1/orders/" + ID_11));
            assertThat(createdOrderDto.getPlacedAt(), notNullValue());
        });
    }

    @Test
    void shouldDeleteOrder_whenIdIsGivenAndOrderExist() {
        //given
        doNothing().when(orderRepository).deleteById(ID_11);

        //when
        orderService.deleteOrder(ID_11);

        //then
        verify(orderRepository, times(1)).deleteById(ID_11);
    }

    @Test
    void shouldFindAllOrdersForSpecificUser_whenUserIdIsGiven() {
        //given
        when(orderRepository.findAllByUserId(anyLong())).thenReturn(orders);

        //when
        OrderListDto actualOrderListDto = orderService.findAllOrdersDtoByUserId(ID_11);

        //then
        assertAll(() -> {
            assertThat(actualOrderListDto, notNullValue());
            assertThat(actualOrderListDto.getOrders().size(), is(2));
            assertThat(actualOrderListDto.getOrders().get(0).getPlacedAt(), notNullValue());
            assertThat(actualOrderListDto.getOrders().get(0).getOrderUrl(), notNullValue());
        });

        verify(orderRepository, times(1)).findAllByUserId(anyLong());
    }
}
