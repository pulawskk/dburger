package com.pulawskk.dburger.controllers.v1;

import com.pulawskk.dburger.api.v1.model.UserDto;
import com.pulawskk.dburger.api.v1.model.UserListDto;
import com.pulawskk.dburger.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserController.USER_BASE_URL)
public class UserController {

    public final static String USER_BASE_URL = "/api/v1/users";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserListDto displayAllUsersDto() {
        return userService.findUsersDto();
    }

    @GetMapping("/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto displayUserByLastName(@PathVariable String lastName) {
        return userService.findUserByLastName(lastName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createNewUser(@RequestBody UserDto userDto) {
        return userService.createNewUser(userDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        return userService.updateUser(id, userDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto patchUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        return userService.patchUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
