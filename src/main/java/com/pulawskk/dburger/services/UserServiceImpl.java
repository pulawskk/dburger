package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.mapper.UserMapper;
import com.pulawskk.dburger.api.v1.model.UserDto;
import com.pulawskk.dburger.api.v1.model.UserListDto;
import com.pulawskk.dburger.domain.User;
import com.pulawskk.dburger.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserListDto findUsersDto() {
        return new UserListDto(userRepository.findAll().stream().map(UserMapper.INSTANCE::userToUserDto).collect(Collectors.toList()));
    }

    @Override
    public UserDto findUserByLastName(String lastName) {
        return UserMapper.INSTANCE.userToUserDto(userRepository.findUserByLastName(lastName));
    }

    @Override
    public UserDto createNewUser(UserDto userDto) {
        User savedUser = userRepository.save(UserMapper.INSTANCE.userDtoToUser(userDto));
        UserDto savedUserDto = UserMapper.INSTANCE.userToUserDto(savedUser);
        savedUserDto.setUserUrl("/api/v1/users/" + savedUser.getId());
        return savedUserDto;
    }
}
