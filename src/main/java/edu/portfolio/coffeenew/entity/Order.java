package edu.portfolio.coffeenew.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "postcode", length = 200, nullable = false)
    private String postcode;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;// 원투 매니로 상대 쪽에 외래키 조건을 걸었지만, 칼럼이 되지는 않음

    @Enumerated(EnumType.STRING) // 이넘형태를 칼럼으로, 오디널은 숫자로, 이건 밸류로 지정 하는 부분 이다.
    @Column(name = "order_status", length = 50, nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public Order() {
    }

    private Category category;

    // 모든 필드를 초기화하는 생성자
    @Builder
    public Order(Long orderId, String email, String address, String postcode, List<OrderItem> orderItems, OrderStatus orderStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}