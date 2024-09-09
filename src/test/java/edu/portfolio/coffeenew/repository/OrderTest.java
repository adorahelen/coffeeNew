package edu.portfolio.coffeenew.repository;

import edu.portfolio.coffeenew.entity.Order;
import edu.portfolio.coffeenew.entity.OrderItem;
import edu.portfolio.coffeenew.entity.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE
, connection = EmbeddedDatabaseConnection.H2)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    private Order order;

    @BeforeEach
    void setUp() {
     //   List<OrderItem> orderItems = new ArrayList<>();
        // orderItems.add(new OrderItem(...)); // 필요에 따라 OrderItem을 추가

        order = Order.builder()
                .email("test@example.com")
                .address("123 Main St")
                .postcode("12345")
               // .orderItems(orderItems)
                .orderStatus(OrderStatus.ACCEPTED)
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    void testSaveOrder() {
        Order savedOrder = orderRepository.save(order);

        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getOrderId()).isNotNull();
        assertThat(savedOrder.getEmail()).isEqualTo(order.getEmail());
        assertThat(savedOrder.getAddress()).isEqualTo(order.getAddress());
        assertThat(savedOrder.getPostcode()).isEqualTo(order.getPostcode());
        assertThat(savedOrder.getOrderStatus()).isEqualTo(order.getOrderStatus());
    }

    @Test
    void testFindById() {
        Order savedOrder = orderRepository.save(order);
        Optional<Order> foundOrder = orderRepository.findById(savedOrder.getOrderId());

        assertThat(foundOrder).isPresent();
        assertThat(foundOrder.get().getOrderId()).isEqualTo(savedOrder.getOrderId());
    }

    @Test
    void testUpdateOrder() {
        Order savedOrder = orderRepository.save(order);
        savedOrder.setAddress("456 New Address");
        savedOrder.setOrderStatus(OrderStatus.SHIPPED);

        Order updatedOrder = orderRepository.save(savedOrder);

        assertThat(updatedOrder.getAddress()).isEqualTo("456 New Address");
        assertThat(updatedOrder.getOrderStatus()).isEqualTo(OrderStatus.SHIPPED);
    }

    @Test
    void testDeleteOrder() {
        Order savedOrder = orderRepository.save(order);
        orderRepository.deleteById(savedOrder.getOrderId());

        Optional<Order> deletedOrder = orderRepository.findById(savedOrder.getOrderId());

        assertThat(deletedOrder).isNotPresent();
    }
}