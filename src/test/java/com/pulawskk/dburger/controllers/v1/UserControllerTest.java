package com.pulawskk.dburger.controllers.v1;

import com.pulawskk.dburger.api.v1.model.UserDto;
import com.pulawskk.dburger.api.v1.model.UserListDto;
import com.pulawskk.dburger.services.UserServiceImpl;
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

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    public final static String LAST_NAME = "last name";

    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;

    MockMvc mockMvc;

    UserDto user1, user2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user1 = new UserDto();
        user1.setId(22L);
        user1.setEmail("myemail@gmail.com");
    }

    @Test
    void displayAllUsersDto() throws Exception {
        //given
        List<UserDto> usersDto = new ArrayList<>();
        user2 = new UserDto();
        user2.setId(23L);
        user2.setEmail("second-email@gmail.com");
        usersDto.add(user1);
        usersDto.add(user2);
        UserListDto userList = new UserListDto(usersDto);

        when(userService.findUsersDto()).thenReturn(userList);

        //then
        mockMvc.perform(get("/api/v1/users/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users", hasSize(2)));
    }

    @Test
    void displayUserByLastName() throws Exception {
        //given
        user1.setLastName(LAST_NAME);
        when(userService.findUserByLastName(LAST_NAME)).thenReturn(user1);

        UserDto actualUserDto = userService.findUserByLastName(LAST_NAME);

        //then
        mockMvc.perform(get("/api/v1/users/" + LAST_NAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)));
    }
}