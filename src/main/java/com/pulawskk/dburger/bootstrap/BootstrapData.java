package com.pulawskk.dburger.bootstrap;

import com.pulawskk.dburger.domain.User;
import com.pulawskk.dburger.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;

    public BootstrapData(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    }
}
