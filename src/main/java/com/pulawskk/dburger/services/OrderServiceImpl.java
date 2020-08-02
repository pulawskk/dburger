package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.mapper.OrderMapper;
import com.pulawskk.dburger.api.v1.model.OrderDto;
import com.pulawskk.dburger.api.v1.model.OrderListDto;
import com.pulawskk.dburger.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderListDto findAllOrdersDto() {
        return new OrderListDto(orderRepository.findAll().stream()
                .map(OrderMapper.INSTANCE::orderToOrderDto)
                .map(o -> {
                    o.setOrderUrl("/api/v1/orders/" + o.getId());
                    o.setUserId(o.getUserId());
                    return o;})
                .collect(Collectors.toList()));
    }

    @Override
    public OrderListDto findAllOrdersDtoByUserId(Long id) {
        return new OrderListDto(orderRepository.findAllByUserId(id).stream()
                .map(OrderMapper.INSTANCE::orderToOrderDto)
                .map(o -> {
                    o.setOrderUrl("/api/v1/orders/" + o.getId());
                    o.setUserId(o.getUserId());
                    return o;})
                .collect(Collectors.toList()));
    }

    @Override
    public OrderDto findOrderById(Long id) {
        return null;
    }

    @Override
    public OrderDto createNewOrder() {
        return null;
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto patchOrder(Long id, OrderDto orderDto) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

    }
}
