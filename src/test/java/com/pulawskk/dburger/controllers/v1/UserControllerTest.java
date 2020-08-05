package com.pulawskk.dburger.controllers.v1;

import com.pulawskk.dburger.api.v1.model.OrderDto;
import com.pulawskk.dburger.api.v1.model.OrderListDto;
import com.pulawskk.dburger.api.v1.model.UserDto;
import com.pulawskk.dburger.api.v1.model.UserListDto;
import com.pulawskk.dburger.controllers.RestResponseEntityExceptionHandler;
import com.pulawskk.dburger.domain.User;
import com.pulawskk.dburger.exceptions.ResourceNotFoundException;
import com.pulawskk.dburger.services.OrderServiceImpl;
import com.pulawskk.dburger.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest extends AbstractRestController {

    public static final Long ID = 23L;
    public final static String FIRST_NAME = "first name";
    public final static String LAST_NAME = "last name";
    public static final String EMAIL = "second-email@gmail.com";
    public static final String DELIVERY_NAME = "Test delivery name";

    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;

    @Mock
    OrderServiceImpl orderService;

    MockMvc mockMvc;

    UserDto user1, user2;

    OrderDto orderDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
        user1 = new UserDto();
        user1.setId(22L);
        user1.setEmail(EMAIL);

        orderDto = new OrderDto();
        orderDto.setDeliveryName(DELIVERY_NAME);
    }

    @Test
    void shouldDisplayAllUsers_whenApiIsCalledWithoutParameters() throws Exception {
        //given
        List<UserDto> usersDto = new ArrayList<>();
        user2 = new UserDto();
        user2.setId(ID);
        user2.setEmail(EMAIL);
        usersDto.add(user1);
        usersDto.add(user2);
        UserListDto userList = new UserListDto(usersDto);

        when(userService.findUsersDto()).thenReturn(userList);

        //then
        mockMvc.perform(get(getUserBaseUrl())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users", hasSize(2)));
    }

    @Test
    void shouldReturnOneUserDto_whenSpecificLastNameIsGivenForGetRequest() throws Exception {
        //given
        user1.setLastName(LAST_NAME);
        when(userService.findUserByLastName(LAST_NAME)).thenReturn(user1);

        //then
        mockMvc.perform(get(getUserBaseUrl() + LAST_NAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)));
    }

    @Test
    void shouldCreateNewUser_whenUserDtoIsGivenInPostRequest() throws Exception {
        //given
        UserDto userDto = new UserDto();
        userDto.setFirstName(FIRST_NAME);
        userDto.setLastName(LAST_NAME);
        userDto.setEmail(EMAIL);
        userDto.setId(ID);
        userDto.setUserUrl(getUserBaseUrl() + ID);

        when(userService.createNewUser(ArgumentMatchers.any(UserDto.class))).thenReturn(userDto);

        //then
        mockMvc.perform(post(getUserBaseUrl())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.email", equalTo(EMAIL)))
                .andExpect(jsonPath("$.user_url", equalTo(getUserBaseUrl() + ID)));
    }

    @Test
    void shouldUpdateWholeUserObject_whenUserDtoIsGivenInPutRequest() throws Exception {
        //given
        UserDto userDto = new UserDto();
        userDto.setFirstName(FIRST_NAME + "update");
        userDto.setLastName(LAST_NAME);
        userDto.setEmail(EMAIL);
        userDto.setId(ID);
        userDto.setUserUrl(getUserBaseUrl() + ID);

        when(userService.updateUser(anyLong(), ArgumentMatchers.any(UserDto.class))).thenReturn(userDto);

        //then
        mockMvc.perform(put(getUserBaseUrl() + ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(userDto)))
                    .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME + "update")))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.email", equalTo(EMAIL)))
                .andExpect(jsonPath("$.user_url", equalTo(getUserBaseUrl() + ID)));
    }

    @Test
    void shouldUpdateUserObject_whenPartOfUserDtoIsGivenInPatchRequest() throws Exception {
        //given
        UserDto userDto = new UserDto();
        userDto.setFirstName(FIRST_NAME + "patch");
        userDto.setLastName(LAST_NAME);
        userDto.setEmail(EMAIL);
        userDto.setId(ID);
        userDto.setUserUrl(getUserBaseUrl() + ID);

        when(userService.patchUser(anyLong(), ArgumentMatchers.any(UserDto.class))).thenReturn(userDto);

        //then
        mockMvc.perform(patch(getUserBaseUrl() + ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME + "patch")))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.email", equalTo(EMAIL)))
                .andExpect(jsonPath("$.user_url", equalTo(getUserBaseUrl() + ID)));
    }

    @Test
    void shouldDeleteUserObject_whenUserIdIsGivenInDeleteRequest() throws Exception {
        //given
        doNothing().when(userService).deleteUser(ID);

        //then
        mockMvc.perform(delete(getUserBaseUrl() + ID))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOneUserDto_whenSpecificIdIsGivenToGetRequestAndUserExists() throws Exception {
        //given
        UserDto userDto = new UserDto();
        userDto.setId(ID);
        userDto.setFirstName(FIRST_NAME);
        userDto.setLastName(LAST_NAME);
        userDto.setEmail(EMAIL);
        userDto.setUserUrl(getUserBaseUrl() + ID);

        when(userService.findUserById(anyLong())).thenReturn(userDto);

        //then
        mockMvc.perform(get(getUserBaseUrl() + ID)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.email", equalTo(EMAIL)))
                .andExpect(jsonPath("$.user_url", equalTo(getUserBaseUrl() + ID)));
    }

    @Test
    void shouldThrowResourceNotFoundException_whenUserIsNotFoundByLastName() throws Exception {
        //given
        when(userService.findUserByLastName(LAST_NAME)).thenThrow(ResourceNotFoundException.class);

        //then
        mockMvc.perform(get(getUserBaseUrl() + LAST_NAME)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.findUserByLastName(LAST_NAME);
        });
    }

    @Test
    void shouldThrowResourceNotFoundException_whenUserIsNotFoundById() throws Exception {
        //given
        when(userService.findUserById(ID)).thenThrow(ResourceNotFoundException.class);

        //then
        mockMvc.perform(get(getUserBaseUrl() + ID)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.findUserById(ID);
        });
    }

    @Test
    void shouldThrowResourceNotFoundException_whenGivenIdIsNegative() throws Exception {
        //given
        Long negativeId = ID * -1L;
        String negativeIdString = String.valueOf(negativeId);

        //then
        mockMvc.perform(get(getUserBaseUrl() + negativeId)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        assertThrows(ResourceNotFoundException.class, () -> {
            userController.displayUserByLastNameOrId(negativeIdString);
        });

        verify(userService, never()).findUserById(ID);
    }

    @Test
    void shouldCreateOrderForSpecificUser_whenOrderDtoAndUserIdAreGiven() throws Exception {
        //given
        when(orderService.createNewOrder(any())).thenReturn(orderDto);

        //then
        mockMvc.perform(post(getUserBaseUrl() + ID + "/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(orderDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.deliveryName", equalTo(DELIVERY_NAME)));
    }

    @Test
    void shouldReturnAllOrdersForSpecificUser_whenUserIdIsGiven() throws Exception {
        //given
        List<OrderDto> orders = new ArrayList<>();
        orders.add(orderDto);
        OrderListDto orderListDto = new OrderListDto(orders);

        when(orderService.findAllOrdersDtoByUserId(anyLong())).thenReturn(orderListDto);

        //then
        mockMvc.perform(get(getUserBaseUrl() + ID + "/orders")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orders", hasSize(1)))
                .andExpect(jsonPath("$.orders[0].deliveryName", equalTo(DELIVERY_NAME)));
    }
}
