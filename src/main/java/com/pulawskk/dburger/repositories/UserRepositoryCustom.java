package com.pulawskk.dburger.repositories;

import com.pulawskk.dburger.domain.User;

public interface UserRepositoryCustom {
    User findUserByLastName(String lastName);
}
