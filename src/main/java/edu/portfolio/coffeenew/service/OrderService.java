package edu.portfolio.coffeenew.service;

import edu.portfolio.coffeenew.entity.Category;
import edu.portfolio.coffeenew.entity.Order;
import edu.portfolio.coffeenew.entity.OrderItem;
import edu.portfolio.coffeenew.entity.OrderStatus;
import edu.portfolio.coffeenew.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }






}
