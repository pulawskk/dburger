package com.pulawskk.dburger.api.v1.mapper;

import com.pulawskk.dburger.api.v1.model.OrderDto;
import com.pulawskk.dburger.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "user.id", target = "userId")
    OrderDto orderToOrderDto(Order order);

    @Mapping(source = "userId", target = "user.id")
    Order orderDtoToOrder(OrderDto orderDto);
}
