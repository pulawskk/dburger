package com.pulawskk.dburger.repositories;

import com.pulawskk.dburger.api.v1.model.UserDto;
import com.pulawskk.dburger.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

@Repository
public class UserRepositoryImpl {
    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;

    User findUserByLastName(String lastName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String sql = "SELECT u FROM User u where u.lastName =:lastName";
        Query query = entityManager.createQuery(sql);
        query.setMaxResults(1);
        query.setParameter("lastName", lastName);
        return (User) query.getSingleResult();
    }
}
