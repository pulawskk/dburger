package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.mapper.UserMapper;
import com.pulawskk.dburger.api.v1.model.UserDto;
import com.pulawskk.dburger.api.v1.model.UserListDto;
import com.pulawskk.dburger.domain.User;
import com.pulawskk.dburger.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserListDto findUsersDto() {
        return new UserListDto(userRepository.findAll().stream().map(userMapper::userToUserDto).collect(Collectors.toList()));
    }

    @Override
    public UserDto findUserByLastName(String lastName) {
        return userMapper.userToUserDto(userRepository.findUserByLastName(lastName));
    }

    @Override
    public UserDto createNewUser(UserDto userDto) {
        User savedUser = userRepository.save(userMapper.userDtoToUser(userDto));
        UserDto savedUserDto = userMapper.userToUserDto(savedUser);
        savedUserDto.setUserUrl("/api/v1/users/" + savedUser.getId());
        return savedUserDto;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        userDto.setId(id);
        User savedUser = userRepository.save(userMapper.userDtoToUser(userDto));
        UserDto savedUserDto = userMapper.userToUserDto(savedUser);
        savedUserDto.setUserUrl("/api/v1/users/" + savedUser.getId());
        return savedUserDto;
    }

    @Override
    public UserDto patchUser(Long id, UserDto userDto) {
        return userRepository.findById(id).map(u -> {
            if (userDto.getFirstName() != null) {
                u.setFirstName(userDto.getFirstName());
            }

            if (userDto.getLastName() != null) {
                u.setLastName(userDto.getLastName());
            }

            if (userDto.getEmail() != null) {
                u.setEmail(userDto.getEmail());
            }

            return userMapper.userToUserDto(userRepository.save(u));
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
