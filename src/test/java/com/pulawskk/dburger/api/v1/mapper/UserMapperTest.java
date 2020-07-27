package com.pulawskk.dburger.api.v1.mapper;

import com.pulawskk.dburger.api.v1.model.UserDto;
import com.pulawskk.dburger.domain.User;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.*;

class UserMapperTest {

    UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    void userToUserDto() {
        //given
        User user = new User();
        user.setId(11L);
        user.setFirstName("admin");
        user.setLastName("adminLast");
        user.setEmail("admin@gmail.com");

        //when
        UserDto userDto = userMapper.userToUserDto(user);

        //then
        assertAll(() -> {
            assertThat(userDto, notNullValue());
            assertThat(userDto.getId(), is(11L));
            assertThat(userDto.getFirstName(), is("admin"));
            assertThat(userDto.getLastName(), is("adminLast"));
            assertThat(userDto.getEmail(), is("admin@gmail.com"));
        });
    }
}