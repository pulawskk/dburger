package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.model.UserDto;
import com.pulawskk.dburger.api.v1.model.UserListDto;
import com.pulawskk.dburger.domain.User;
import com.pulawskk.dburger.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    public static final long ID = 12L;
    public static final String FIRST_NAME = "test name";
    public static final String LAST_NAME = "test last name";
    public static final String EMAIL = "test-email@gmail.com";
    private User user1, user2;
    private List<User> users = new ArrayList<>();

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
        user1 = new User();
        user1.setId(ID);
        user1.setFirstName(FIRST_NAME);
        user1.setLastName(LAST_NAME);
        user1.setEmail(EMAIL);

    }

    @Test
    void findUsersDto() {
        //given
        user2 = new User();
        user2.setId(ID + 2);
        user2.setFirstName(FIRST_NAME + 2);
        user2.setLastName(LAST_NAME + 2);
        user2.setEmail(2 + EMAIL);
        users.add(user1);
        users.add(user2);
        when(userRepository.findAll()).thenReturn(users);

        //when
        UserListDto userList = userService.findUsersDto();

        //then
        assertAll(() -> {
            assertThat(userList, notNullValue());
            assertThat(userList.getUsers().size(), is(2));
        });
    }

    @Test
    void findUserByLastName() {
        //given
        when(userRepository.findUserByLastName(LAST_NAME)).thenReturn(user1);

        //when
        UserDto userDto = userService.findUserByLastName(LAST_NAME);

        //then
        assertAll(() -> {
            assertThat(userDto, notNullValue());
            assertThat(userDto.getId(), is(ID));
            assertThat(userDto.getFirstName(), is(FIRST_NAME));
            assertThat(userDto.getLastName(), is(LAST_NAME));
            assertThat(userDto.getEmail(), is(EMAIL));
        });
    }
}