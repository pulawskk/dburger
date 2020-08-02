package com.pulawskk.dburger.controllers.v1;

import com.pulawskk.dburger.api.v1.model.OrderDto;
import com.pulawskk.dburger.api.v1.model.OrderListDto;
import com.pulawskk.dburger.services.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.pulawskk.dburger.controllers.v1.AbstractRestController.getOrderBaseUrl;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderServiceImpl orderService;

    MockMvc mockMvc;

    OrderDto orderDto1, orderDto2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        orderDto1 = new OrderDto();
        orderDto2 = new OrderDto();
    }

    @Test
    void shouldDisplayAllOrders_whenGetRequestIsInvoked() throws Exception {
        //given
        List<OrderDto> orderDtoList = new ArrayList<>();
        orderDtoList.add(orderDto1);
        orderDtoList.add(orderDto2);

        OrderListDto orderListDto = new OrderListDto(orderDtoList);

        when(orderService.findAllOrdersDto()).thenReturn(orderListDto);

        //then
        mockMvc.perform(get(getOrderBaseUrl())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orders", hasSize(2)));

    }
}
