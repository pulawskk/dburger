package com.pulawskk.dburger.api.v1.mapper;

import com.pulawskk.dburger.api.v1.model.OrderDto;
import com.pulawskk.dburger.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto orderToOrderDto(Order order);

    Order orderDtoToOrder(OrderDto orderDto);
}