package com.pulawskk.dburger.api.v1.mapper;

import com.pulawskk.dburger.api.v1.model.UserDto;
import com.pulawskk.dburger.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
