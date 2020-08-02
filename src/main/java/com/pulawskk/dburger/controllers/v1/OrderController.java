package com.pulawskk.dburger.controllers.v1;

import com.pulawskk.dburger.api.v1.model.OrderDto;
import com.pulawskk.dburger.api.v1.model.OrderListDto;
import com.pulawskk.dburger.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(OrderController.ORDER_BASE_URL)
@CrossOrigin
public class OrderController {
    public final static String ORDER_BASE_URL = "/api/v1/orders";

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderListDto displayAllOrders() {
        return orderService.findAllOrdersDto();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto displayOrderDtoById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }


}
