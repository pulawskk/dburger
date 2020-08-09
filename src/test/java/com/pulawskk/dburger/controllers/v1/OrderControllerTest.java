package com.pulawskk.dburger.controllers.v1;

import com.pulawskk.dburger.api.v1.model.OrderDto;
import com.pulawskk.dburger.api.v1.model.OrderListDto;
import com.pulawskk.dburger.exceptions.ResourceNotFoundException;
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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    public static final String DELIVERY_NAME = "Delivery test name";
    public static final long ID = 11L;

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
        orderDto1.setId(ID);
        orderDto1.setDeliveryName(DELIVERY_NAME);
        orderDto1.setOrderUrl(getOrderBaseUrl() + ID);
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

    @Test
    void shouldDisplayOrder_whenGetRequestIsInvokedWithOrderId() throws Exception {
        //given
        when(orderService.findOrderById(anyLong())).thenReturn(orderDto1);

        //then
        mockMvc.perform(get(getOrderBaseUrl() + ID)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deliveryName", equalTo(DELIVERY_NAME)))
                .andExpect(jsonPath("$.order_url", equalTo(getOrderBaseUrl() + ID)));
    }

    @Test
    void shouldDeleteOrder_whenOrderIdIsGivenOnDeleteRequest() throws Exception {
        //given
        doNothing().when(orderService).deleteOrder(anyLong());

        //then
        mockMvc.perform(delete(getOrderBaseUrl() + anyLong())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
