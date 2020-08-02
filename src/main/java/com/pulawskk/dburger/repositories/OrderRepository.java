package com.pulawskk.dburger.repositories;

import com.pulawskk.dburger.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
