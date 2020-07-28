package com.pulawskk.dburger.controllers.v1;

import com.pulawskk.dburger.api.v1.model.UserDto;
import com.pulawskk.dburger.api.v1.model.UserListDto;
import com.pulawskk.dburger.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserListDto> displayAllUsersDto() {
        return new ResponseEntity<>(userService.findUsersDto(), HttpStatus.OK);
    }

    @GetMapping("/{lastName}")
    public ResponseEntity<UserDto> displayUserByLastName(@PathVariable String lastName) {
        return new ResponseEntity<>(userService.findUserByLastName(lastName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createNewUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createNewUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> patchUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        return new ResponseEntity<>(userService.patchUser(id, userDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
