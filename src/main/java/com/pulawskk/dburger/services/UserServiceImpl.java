package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.mapper.UserMapper;
import com.pulawskk.dburger.api.v1.model.UserDto;
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
    public List<UserDto> findUsersDto() {
        return userRepository.findAll().stream().map(UserMapper.INSTANCE::userToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findUserByLastName(String lastName) {
        return UserMapper.INSTANCE.userToUserDto(userRepository.findUserByLastName(lastName));
    }
}
