package com.pulawskk.dburger.api.v1.mapper;

import com.pulawskk.dburger.api.v1.model.OrderDto;
import com.pulawskk.dburger.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {

    public static final long ID = 31L;
    public static final String DELIVERY_NAME = "Test delivery";
    public static final String DELIVERY_STATE = "My state";
    public static final String DELIVERY_CITY = "My city";
    public static final String DELIVERY_ZIP = "test zip";
    public static final String DELIVERY_STREET = "st test";
    public static final String CC_NUMBER = "1234123412341234";
    public static final String CC_EXPIRATION = "06-22";
    public static final String CC_CVV = "123";

    private final OrderMapper orderMapper = OrderMapper.INSTANCE;

    @Test
    void shouldConvertOrderToOrderDto_whenOrderIsGiven() {
        //given
        Order order = new Order();
        order.setId(ID);
        order.setDeliveryName(DELIVERY_NAME);
        order.setDeliveryState(DELIVERY_STATE);
        order.setDeliveryCity(DELIVERY_CITY);
        order.setDeliveryZIP(DELIVERY_ZIP);
        order.setDeliveryStreet(DELIVERY_STREET);
        order.setCcNumber(CC_NUMBER);
        order.setCcExpiration(CC_EXPIRATION);
        order.setCcCVV(CC_CVV);

        //when
        OrderDto orderDto = orderMapper.orderToOrderDto(order);

        //then
        assertAll(() -> {
            assertThat(orderDto, notNullValue());
            assertThat(orderDto.getId(), is(ID));
            assertThat(orderDto.getDeliveryName(), is(DELIVERY_NAME));
            assertThat(orderDto.getDeliveryState(), is(DELIVERY_STATE));
            assertThat(orderDto.getDeliveryCity(), is(DELIVERY_CITY));
            assertThat(orderDto.getDeliveryStreet(), is(DELIVERY_STREET));
            assertThat(orderDto.getDeliveryZIP(), is(DELIVERY_ZIP));
            assertThat(orderDto.getCcNumber(), is(CC_NUMBER));
            assertThat(orderDto.getCcExpiration(), is(CC_EXPIRATION));
            assertThat(orderDto.getCcCVV(), is(CC_CVV));
        });
    }

    @Test
    void shouldConvertOrderDtoToOrder_whenOrderDtoIsGiven() {
        //given
        OrderDto orderDto = new OrderDto();
        orderDto.setId(ID);
        orderDto.setDeliveryName(DELIVERY_NAME);
        orderDto.setDeliveryState(DELIVERY_STATE);
        orderDto.setDeliveryCity(DELIVERY_CITY);
        orderDto.setDeliveryZIP(DELIVERY_ZIP);
        orderDto.setDeliveryStreet(DELIVERY_STREET);
        orderDto.setCcNumber(CC_NUMBER);
        orderDto.setCcExpiration(CC_EXPIRATION);
        orderDto.setCcCVV(CC_CVV);

        //when
        Order order = orderMapper.orderDtoToOrder(orderDto);

        //then
        assertAll(() -> {
            assertThat(order, notNullValue());
            assertThat(order.getId(), is(ID));
            assertThat(order.getDeliveryName(), is(DELIVERY_NAME));
            assertThat(order.getDeliveryState(), is(DELIVERY_STATE));
            assertThat(order.getDeliveryCity(), is(DELIVERY_CITY));
            assertThat(order.getDeliveryStreet(), is(DELIVERY_STREET));
            assertThat(order.getDeliveryZIP(), is(DELIVERY_ZIP));
            assertThat(order.getCcNumber(), is(CC_NUMBER));
            assertThat(order.getCcExpiration(), is(CC_EXPIRATION));
            assertThat(order.getCcCVV(), is(CC_CVV));
        });
    }
}
