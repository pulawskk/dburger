package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.model.UserDto;
import com.pulawskk.dburger.api.v1.model.UserListDto;

import java.util.List;

public interface UserService {

    UserListDto findUsersDto();

    UserDto findUserById(Long id);

    UserDto findUserByLastName(String lastName);

    UserDto createNewUser(UserDto userDto);

    UserDto updateUser(Long id, UserDto userDto);

    UserDto patchUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}
