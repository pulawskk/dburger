package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.model.UserDto;
import com.pulawskk.dburger.api.v1.model.UserListDto;

import java.util.List;

public interface UserService {

    UserListDto findUsersDto();

    UserDto findUserByLastName(String lastName);

    UserDto createNewUser(UserDto userDto);
}
