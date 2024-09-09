package edu.portfolio.coffeenew.service;

import edu.portfolio.coffeenew.controller.OrderItemRequest;
import edu.portfolio.coffeenew.entity.*;
import edu.portfolio.coffeenew.repository.OrderRepository;
import edu.portfolio.coffeenew.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }



        public Order createOrder(String email, String address, String postcode, List<OrderItemRequest> orderItemRequests) {
            // Order 엔티티 생성
            Order order = Order.builder()
                    .email(email)
                    .address(address)
                    .postcode(postcode)
                    .orderStatus(OrderStatus.ACCEPTED) // 기본 상태 설정
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            // OrderItem 엔티티 생성 및 설정
            List<OrderItem> orderItems = orderItemRequests.stream().map(request -> {
                Product product = productRepository.findById(request.getProductId())
                        .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + request.getProductId()));

                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProduct(product);
                orderItem.setCategory(request.getCategory());
                orderItem.setPrice(request.getPrice());
                orderItem.setQuantity(request.getQuantity());
                orderItem.setCreatedAt(LocalDateTime.now());
                orderItem.setUpdatedAt(LocalDateTime.now());

                return orderItem;
            }).collect(Collectors.toList());

            order.setOrderItems(orderItems);

            // Order 저장 (OrderItems도 함께 저장됨)
            return orderRepository.save(order);
        }
    }




