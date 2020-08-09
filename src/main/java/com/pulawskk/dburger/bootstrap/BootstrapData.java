package com.pulawskk.dburger.bootstrap;

import com.github.javafaker.Faker;
import com.pulawskk.dburger.domain.Burger;
import com.pulawskk.dburger.domain.Ingredient;
import com.pulawskk.dburger.domain.Order;
import com.pulawskk.dburger.domain.User;
import com.pulawskk.dburger.enums.IngredientType;
import com.pulawskk.dburger.repositories.BurgerRepository;
import com.pulawskk.dburger.repositories.IngredientRepository;
import com.pulawskk.dburger.repositories.OrderRepository;
import com.pulawskk.dburger.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final IngredientRepository ingredientRepository;
    private final BurgerRepository burgerRepository;
    private final Faker faker;

    public BootstrapData(UserRepository userRepository, OrderRepository orderRepository, IngredientRepository ingredientRepository, BurgerRepository burgerRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.ingredientRepository = ingredientRepository;
        this.burgerRepository = burgerRepository;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Dohn");
        user1.setEmail("jd@gmail.com");
        User user2 = new User();
        user2.setFirstName("Eva");
        user2.setLastName("Evans");
        user2.setEmail("ee@gmail.com");
        User user3 = new User();
        user3.setFirstName("Sammy");
        user3.setLastName("Tammy");
        user3.setEmail("st@gmail.com");
        User user4 = new User();
        user4.setFirstName("Toni");
        user4.setLastName("Boni");
        user4.setEmail("tb@gmail.com");

        User savedUser1 = userRepository.save(user1);
        User savedUser2 = userRepository.save(user2);
        User savedUser3 = userRepository.save(user3);
        User savedUser4 = userRepository.save(user4);

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
        order1.setUser(savedUser1);

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
        order2.setUser(savedUser1);

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
        order3.setUser(savedUser3);

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
        order4.setUser(savedUser3);
        
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setIngredientType(IngredientType.ROLL);
        ingredient1.setName("wheat");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setIngredientType(IngredientType.ROLL);
        ingredient2.setName("rye");

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setIngredientType(IngredientType.MEAT);
        ingredient3.setName("rare");

        Ingredient ingredient4 = new Ingredient();
        ingredient4.setIngredientType(IngredientType.MEAT);
        ingredient4.setName("medium");

        Ingredient ingredient5 = new Ingredient();
        ingredient5.setIngredientType(IngredientType.MEAT);
        ingredient5.setName("well done");

        Ingredient ingredient6 = new Ingredient();
        ingredient6.setIngredientType(IngredientType.VEGETABLE);
        ingredient6.setName("tomato");

        Ingredient ingredient7 = new Ingredient();
        ingredient7.setIngredientType(IngredientType.VEGETABLE);
        ingredient7.setName("lettuce");

        Ingredient ingredient8 = new Ingredient();
        ingredient8.setIngredientType(IngredientType.VEGETABLE);
        ingredient8.setName("onion");

        Ingredient ingredient9 = new Ingredient();
        ingredient9.setIngredientType(IngredientType.ADDITION);
        ingredient9.setName("cheese");

        Ingredient ingredient10 = new Ingredient();
        ingredient10.setIngredientType(IngredientType.ADDITION);
        ingredient10.setName("bacon");

        Ingredient ingredient11 = new Ingredient();
        ingredient11.setIngredientType(IngredientType.SAUCE);
        ingredient11.setName("ketchup");

        Ingredient ingredient12 = new Ingredient();
        ingredient12.setIngredientType(IngredientType.SAUCE);
        ingredient12.setName("mayo");

        Ingredient savedIngredient1 = ingredientRepository.save(ingredient1);
        Ingredient savedIngredient2 = ingredientRepository.save(ingredient2);
        Ingredient savedIngredient3 = ingredientRepository.save(ingredient3);
        Ingredient savedIngredient4 = ingredientRepository.save(ingredient4);
        Ingredient savedIngredient5 = ingredientRepository.save(ingredient5);
        Ingredient savedIngredient6 = ingredientRepository.save(ingredient6);
        Ingredient savedIngredient7 = ingredientRepository.save(ingredient7);
        Ingredient savedIngredient8 = ingredientRepository.save(ingredient8);
        Ingredient savedIngredient9 = ingredientRepository.save(ingredient9);
        Ingredient savedIngredient10 = ingredientRepository.save(ingredient10);
        Ingredient savedIngredient11 = ingredientRepository.save(ingredient11);
        Ingredient savedIngredient12 = ingredientRepository.save(ingredient12);

        Burger burger1 = new Burger();
        burger1.setName("classic");
        burger1.setPrice(new BigDecimal("20.99"));
        burgerRepository.save(burger1);
        burger1.addIngredient(savedIngredient1);
        burger1.addIngredient(savedIngredient4);
        burger1.addIngredient(savedIngredient7);
        burger1.addIngredient(savedIngredient8);
        burger1.addIngredient(savedIngredient11);

        Burger burger2 = new Burger();
        burger2.setName("cheese");
        burger2.setPrice(new BigDecimal("23.99"));
        burgerRepository.save(burger2);
        burger2.addIngredient(savedIngredient1);
        burger2.addIngredient(savedIngredient4);
        burger2.addIngredient(savedIngredient6);
        burger2.addIngredient(savedIngredient7);
        burger2.addIngredient(savedIngredient8);
        burger2.addIngredient(savedIngredient10);
        burger2.addIngredient(savedIngredient11);
        burger2.addIngredient(savedIngredient12);

        Burger burger3 = new Burger();
        burger3.setName("light");
        burger3.setPrice(new BigDecimal("22.99"));
        burgerRepository.save(burger3);
        burger3.addIngredient(savedIngredient2);
        burger3.addIngredient(savedIngredient5);
        burger3.addIngredient(savedIngredient6);
        burger3.addIngredient(savedIngredient7);
        burger3.addIngredient(savedIngredient8);
        burger3.addIngredient(savedIngredient11);

        Burger burger4 = new Burger();
        burger4.setName("mega");
        burger4.setPrice(new BigDecimal("26.99"));
        burgerRepository.save(burger4);
        burger4.addIngredient(savedIngredient2);
        burger4.addIngredient(savedIngredient5);
        burger4.addIngredient(savedIngredient6);
        burger4.addIngredient(savedIngredient7);
        burger4.addIngredient(savedIngredient8);
        burger4.addIngredient(savedIngredient9);
        burger4.addIngredient(savedIngredient10);
        burger4.addIngredient(savedIngredient11);
        burger4.addIngredient(savedIngredient12);

        burgerRepository.saveAndFlush(burger1);
        burgerRepository.saveAndFlush(burger2);
        burgerRepository.saveAndFlush(burger3);
        burgerRepository.saveAndFlush(burger4);

        order1.addBurger(burger1);
        order2.addBurger(burger2);
        order3.addBurger(burger3);
        order4.addBurger(burger4);
        orderRepository.saveAndFlush(order1);
        orderRepository.saveAndFlush(order2);
        orderRepository.saveAndFlush(order3);
        orderRepository.saveAndFlush(order4);
    }
}
