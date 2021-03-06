package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.mapper.UserMapper;
import com.pulawskk.dburger.api.v1.model.UserDto;
import com.pulawskk.dburger.api.v1.model.UserListDto;
import com.pulawskk.dburger.controllers.v1.UserController;
import com.pulawskk.dburger.domain.User;
import com.pulawskk.dburger.exceptions.ResourceNotFoundException;
import com.pulawskk.dburger.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static String getUserBaseUrl() {
        return UserController.USER_BASE_URL + "/";
    }

    @Override
    public UserListDto findUsersDto() {
        return new UserListDto(userRepository.findAll().stream()
                .map(UserMapper.INSTANCE::userToUserDto)
                .map(setUrlForUserDto)
                .collect(Collectors.toList()));
    }

    @Override
    public UserDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User with id: " + id + " does not exist."));
        UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);
        userDto.setUserUrl(getUserBaseUrl() + userDto.getId());
        return userDto;
    }

    private final Function<UserDto, UserDto> setUrlForUserDto = (userDto) -> {
        userDto.setUserUrl(getUserBaseUrl() + userDto.getId());
        return userDto;
    };

    @Override
    public UserDto findUserByLastName(String lastName) {
        User user = userRepository.findUserByLastName(lastName);
        if (user == null) {
            throw new ResourceNotFoundException("User with last name: " + lastName + " does not exist");
        }
        UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);
        userDto.setUserUrl(getUserBaseUrl() + userDto.getId());
        return userDto;
    }

    @Override
    public UserDto createNewUser(UserDto userDto) {
        User savedUser = userRepository.save(UserMapper.INSTANCE.userDtoToUser(userDto));
        UserDto savedUserDto = UserMapper.INSTANCE.userToUserDto(savedUser);
        savedUserDto.setUserUrl(getUserBaseUrl() + savedUser.getId());
        return savedUserDto;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        userDto.setId(id);
        User savedUser = userRepository.save(UserMapper.INSTANCE.userDtoToUser(userDto));
        UserDto savedUserDto = UserMapper.INSTANCE.userToUserDto(savedUser);
        savedUserDto.setUserUrl(getUserBaseUrl() + savedUser.getId());
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

            return UserMapper.INSTANCE.userToUserDto(userRepository.save(u));
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
