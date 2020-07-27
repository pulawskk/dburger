package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.model.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findUsersDto();

    UserDto findUserByLastName(String lastName);
}
