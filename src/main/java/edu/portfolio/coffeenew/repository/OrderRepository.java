package edu.portfolio.coffeenew.repository;

import edu.portfolio.coffeenew.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
