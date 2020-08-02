package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.model.OrderDto;
import com.pulawskk.dburger.api.v1.model.OrderListDto;

public interface OrderService {
    OrderListDto findAllOrdersDto();

    OrderListDto findAllOrdersDtoByUserId(Long id);

    OrderDto findOrderById(Long id);

    OrderDto createNewOrder();

    OrderDto updateOrder(Long id, OrderDto orderDto);

    OrderDto patchOrder(Long id, OrderDto orderDto);

    void deleteOrder(Long id);
}
