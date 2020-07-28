package com.pulawskk.dburger.api.v1.mapper;

import com.pulawskk.dburger.api.v1.model.UserDto;
import com.pulawskk.dburger.domain.User;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.*;

class UserMapperTest {

    public static final long ID = 11L;
    public static final String FIRST_NAME = "admin";
    public static final String LAST_NAME = "adminLast";
    public static final String EMAIL = "admin@gmail.com";
    UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    void shouldConvertUserToUserDto_whenUserIsGiven() {
        //given
        User user = new User();
        user.setId(ID);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);

        //when
        UserDto userDto = userMapper.userToUserDto(user);

        //then
        assertAll(() -> {
            assertThat(userDto, notNullValue());
            assertThat(userDto.getId(), is(ID));
            assertThat(userDto.getFirstName(), is(FIRST_NAME));
            assertThat(userDto.getLastName(), is(LAST_NAME));
            assertThat(userDto.getEmail(), is(EMAIL));
        });
    }

    @Test
    public void shouldConvertUserDtoToUser_whenUserDtoIsGiven() {
         //given
        UserDto userDto = new UserDto();
        userDto.setFirstName(FIRST_NAME);
        userDto.setLastName(LAST_NAME);
        userDto.setEmail(EMAIL);

        //when
        User user = userMapper.userDtoToUser(userDto);

        //then
        assertAll(() -> {
            assertThat(user, notNullValue());
            assertThat(user.getFirstName(), is(FIRST_NAME));
            assertThat(user.getLastName(), is(LAST_NAME));
            assertThat(user.getEmail(), is(EMAIL));
        });
    }
}