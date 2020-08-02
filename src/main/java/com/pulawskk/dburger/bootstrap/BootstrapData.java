package com.pulawskk.dburger.bootstrap;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.pulawskk.dburger.domain.Order;
import com.pulawskk.dburger.domain.User;
import com.pulawskk.dburger.repositories.OrderRepository;
import com.pulawskk.dburger.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;

@Component
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final Faker faker;

    public BootstrapData(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("John");
        user1.setLastName("Dohn");
        user1.setEmail("jd@gmail.com");
        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Eva");
        user2.setLastName("Evans");
        user2.setEmail("ee@gmail.com");
        User user3 = new User();
        user3.setId(3L);
        user3.setFirstName("Sammy");
        user3.setLastName("Tammy");
        user3.setEmail("st@gmail.com");
        User user4 = new User();
        user4.setId(4L);
        user4.setFirstName("Toni");
        user4.setLastName("Boni");
        user4.setEmail("tb@gmail.com");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        Order order1 = new Order();
        order1.setCcCVV("123");
        order1.setCcExpiration(faker.business().creditCardExpiry());
        order1.setCcNumber(faker.finance().creditCard());
        order1.setDeliveryCity(faker.address().city());
        order1.setDeliveryState(faker.address().state());
        order1.setDeliveryStreet(faker.address().streetName());
        order1.setDeliveryZIP(faker.address().zipCode());
        order1.setDeliveryName(faker.name().title());
        order1.setPlacedAt(LocalDateTime.now());
        order1.setUser(user1);

        Order order2 = new Order();
        order2.setCcCVV("223");
        order2.setCcExpiration(faker.business().creditCardExpiry());
        order2.setCcNumber(faker.finance().creditCard());
        order2.setDeliveryCity(faker.address().city());
        order2.setDeliveryState(faker.address().state());
        order2.setDeliveryStreet(faker.address().streetName());
        order2.setDeliveryZIP(faker.address().zipCode());
        order2.setDeliveryName(faker.name().title());
        order2.setPlacedAt(LocalDateTime.now());
        order2.setUser(user2);

        Order order3 = new Order();
        order3.setCcCVV("323");
        order3.setCcExpiration(faker.business().creditCardExpiry());
        order3.setCcNumber(faker.finance().creditCard());
        order3.setDeliveryCity(faker.address().city());
        order3.setDeliveryState(faker.address().state());
        order3.setDeliveryStreet(faker.address().streetName());
        order3.setDeliveryZIP(faker.address().zipCode());
        order3.setDeliveryName(faker.name().title());
        order3.setPlacedAt(LocalDateTime.now());
        order3.setUser(user3);

        Order order4 = new Order();
        order4.setCcCVV("423");
        order4.setCcExpiration(faker.business().creditCardExpiry());
        order4.setCcNumber(faker.finance().creditCard());
        order4.setDeliveryCity(faker.address().city());
        order4.setDeliveryState(faker.address().state());
        order4.setDeliveryStreet(faker.address().streetName());
        order4.setDeliveryZIP(faker.address().zipCode());
        order4.setDeliveryName(faker.name().title());
        order4.setPlacedAt(LocalDateTime.now());
        order4.setUser(user4);
        
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);
    }
}
