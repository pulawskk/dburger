package com.pulawskk.dburger.controllers.v1;

import com.pulawskk.dburger.api.v1.model.OrderDto;
import com.pulawskk.dburger.api.v1.model.OrderListDto;
import com.pulawskk.dburger.api.v1.model.UserDto;
import com.pulawskk.dburger.api.v1.model.UserListDto;
import com.pulawskk.dburger.exceptions.ResourceNotFoundException;
import com.pulawskk.dburger.services.OrderService;
import com.pulawskk.dburger.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(UserController.USER_BASE_URL)
@CrossOrigin
public class UserController {

    public final static String USER_BASE_URL = "/api/v1/users";

    private final UserService userService;
    private final OrderService orderService;

    public UserController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserListDto displayAllUsersDto() {
        return userService.findUsersDto();
    }

    @GetMapping("/{param}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto displayUserByLastNameOrId(@PathVariable String param) {
        try {
            Long id = Long.parseLong(param);
            if (id < 1) {
                throw new ResourceNotFoundException("Id must be greater than 0");
            }
            return userService.findUserById(id);
        } catch (NumberFormatException e) {
            return userService.findUserByLastName(param);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/orders")
    public OrderListDto displayAllOrdersForSpecificUser(@PathVariable Long id) {
        return orderService.findAllOrdersDtoByUserId(id);
    }

    @PostMapping("/{id}/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody OrderDto orderDto, @PathVariable Long id) {
        orderDto.setUserId(id);
        System.out.println(orderDto.getBurgerId());
        return orderService.createNewOrder(orderDto);
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
