package edu.portfolio.coffeenew.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString // 추가하면 Product@7a80ebcd -> productId=1252, productName=New
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")  // 테이블 이름을 'products'로 설정
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long productId;
    // 처음에 오토인크리먼트 안해놔서 (이미 구현됨, 지금은 저거 넣으면 에러남)
    // 이전에는 랜덤한 값이 들어갔는데, 왜 이번에는 오토 인크리먼트가 들어갔는지 모르겠다
    //before : 220,552 등의 랜덤값 -> after : 1-2-3 (drop 한 다음 다시 만듬)

    @Column(name = "product_name", length = 20, nullable = false)
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", length = 50, nullable = false)
    private Category category;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

