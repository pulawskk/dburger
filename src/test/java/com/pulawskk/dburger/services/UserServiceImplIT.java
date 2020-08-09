package com.pulawskk.dburger.services;

import com.pulawskk.dburger.api.v1.model.UserDto;
import com.pulawskk.dburger.bootstrap.BootstrapData;
import com.pulawskk.dburger.domain.User;
import com.pulawskk.dburger.repositories.IngredientRepository;
import com.pulawskk.dburger.repositories.OrderRepository;
import com.pulawskk.dburger.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceImplIT {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    UserServiceImpl userService;

    @BeforeEach
    void setUp() throws Exception {
        BootstrapData bootstrapData = new BootstrapData(userRepository, orderRepository, ingredientRepository);
        bootstrapData.run();

        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void shouldPatchUserFirstName_whenOnlyFirstNameIsGiven() {
        //given
        Long id = getLatestUserIdFromDb();
        String firstNameToUpdate = "John with updated name";

        User originalUser = userRepository.getOne(id);
        assertNotNull(originalUser);
        String originalFirstName = originalUser.getFirstName();

        UserDto userDto = new UserDto();
        userDto.setFirstName(firstNameToUpdate);

        //when
        userService.patchUser(id, userDto);
        User patchedUser = userRepository.findById(id).orElseGet(User::new);

        //then
        assertAll(() -> {
            assertThat(patchedUser, notNullValue());
            assertThat(patchedUser.getFirstName(), is(firstNameToUpdate));
            assertThat(patchedUser.getFirstName(), not(originalFirstName));
            assertThat(patchedUser.getLastName(), is(originalUser.getLastName()));
            assertThat(patchedUser.getEmail(), is(originalUser.getEmail()));
        });
    }

    /**
     * Helper method to get id for latest added user
     * @return id for User object
     */
    private Long getLatestUserIdFromDb() {
        return userRepository.findAll().get(0).getId();
    }

    @Test
    void shouldPatchUserLastName_whenOnlyLastNameIsGiven() {
        //given
        Long id = getLatestUserIdFromDb();
        String lastNameToUpdate = "Dohnyy with updated last name";

        User originalUser = userRepository.getOne(id);
        assertNotNull(originalUser);
        String originalLastName = originalUser.getLastName();

        UserDto userDto = new UserDto();
        userDto.setLastName(lastNameToUpdate);

        //when
        userService.patchUser(id, userDto);
        User patchedUser = userRepository.findById(id).orElseGet(User::new);

        //then
        assertAll(() -> {
            assertThat(patchedUser, notNullValue());
            assertThat(patchedUser.getLastName(), is(lastNameToUpdate));
            assertThat(patchedUser.getLastName(), not(originalLastName));
            assertThat(patchedUser.getFirstName(), is(originalUser.getFirstName()));
            assertThat(patchedUser.getEmail(), is(originalUser.getEmail()));
        });
    }

    @Test
    void shouldPatchUserEmail_whenOnlyEmailIsGiven() {
        //given
        Long id = getLatestUserIdFromDb();
        String emailToUpdate = "emailIsBeingPatched@gmail.com";

        User originalUser = userRepository.getOne(id);
        assertNotNull(originalUser);
        String originalEmail = originalUser.getEmail();

        UserDto userDto = new UserDto();
        userDto.setEmail(emailToUpdate);

        //when
        userService.patchUser(id, userDto);
        User patchedUser = userRepository.findById(id).orElseGet(User::new);

        //then
        assertAll(() -> {
            assertThat(patchedUser, notNullValue());
            assertThat(patchedUser.getEmail(), is(emailToUpdate));
            assertThat(patchedUser.getEmail(), not(originalEmail));
            assertThat(patchedUser.getFirstName(), is(originalUser.getFirstName()));
            assertThat(patchedUser.getLastName(), is(originalUser.getLastName()));
        });
    }
}
