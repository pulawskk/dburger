package com.pulawskk.dburger.repositories;

import com.pulawskk.dburger.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByLastName(String lastName);
}
