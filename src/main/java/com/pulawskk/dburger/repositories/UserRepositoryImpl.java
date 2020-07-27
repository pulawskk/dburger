package com.pulawskk.dburger.repositories;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

@Repository
public class UserRepositoryImpl {
    @PersistenceUnit
    EntityManager entityManager;
}
