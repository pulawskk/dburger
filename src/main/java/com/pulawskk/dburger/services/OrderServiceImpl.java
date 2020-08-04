package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.mapper.OrderMapper;
import com.pulawskk.dburger.api.v1.model.OrderDto;
import com.pulawskk.dburger.api.v1.model.OrderListDto;
import com.pulawskk.dburger.domain.Order;
import com.pulawskk.dburger.exceptions.ResourceNotFoundException;
import com.pulawskk.dburger.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
        return OrderMapper.INSTANCE.
                orderToOrderDto(orderRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public OrderDto createNewOrder(OrderDto orderDto) {
        Order orderToBeSaved = OrderMapper.INSTANCE.orderDtoToOrder(orderDto);
        Order savedOrder = orderRepository.save(orderToBeSaved);

        OrderDto savedOrderDto = OrderMapper.INSTANCE.orderToOrderDto(savedOrder);
        savedOrderDto.setOrderUrl("/api/v1/orders/" + savedOrder.getId());

        return savedOrderDto;
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto patchOrder(Long id, OrderDto orderDto) {
        OrderDto actualOrder = OrderMapper.INSTANCE.orderToOrderDto(
                orderRepository.findById(id).orElseThrow(ResourceNotFoundException::new));

        Optional.of(orderDto.getDeliveryName()).ifPresent(o -> {
            if (!o.isBlank()) {
                actualOrder.setDeliveryName(o);
            }
        });

        Optional.of(orderDto.getDeliveryState()).ifPresent(o -> {
            if (!o.isBlank()) {
                actualOrder.setDeliveryState(o);
            }
        });

        Optional.of(orderDto.getDeliveryCity()).ifPresent(o -> {
            if (!o.isBlank()) {
                actualOrder.setDeliveryCity(o);
            }
        });

        Optional.of(orderDto.getDeliveryStreet()).ifPresent(o -> {
            if (!o.isBlank()) {
                actualOrder.setDeliveryStreet(o);
            }
        });

        Optional.of(orderDto.getDeliveryZIP()).ifPresent(o -> {
            if (!o.isBlank()) {
                actualOrder.setDeliveryZIP(o);
            }
        });

        Optional.of(orderDto.getCcNumber()).ifPresent(o -> {
            if (!o.isBlank()) {
                actualOrder.setCcNumber(o);
            }
        });

        Optional.of(orderDto.getCcExpiration()).ifPresent(o -> {
            if (!o.isBlank()) {
                actualOrder.setCcExpiration(o);
            }
        });

        Optional.of(orderDto.getCcCVV()).ifPresent(o -> {
            if (!o.isBlank()) {
                actualOrder.setCcCVV(o);
            }
        });

        Order savedOrder = orderRepository.save(OrderMapper.INSTANCE.orderDtoToOrder(actualOrder));

        return OrderMapper.INSTANCE.orderToOrderDto(savedOrder);
    }

    @Override
    public void deleteOrder(Long id) {

    }
}
