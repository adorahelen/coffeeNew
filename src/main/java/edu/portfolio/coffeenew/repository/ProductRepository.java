package edu.portfolio.coffeenew.repository;

import edu.portfolio.coffeenew.entity.Category;
import edu.portfolio.coffeenew.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String productName);
    List<Product> findByCategory(Category category);

    // 테이블 이름이랑 맞춰야 한다. -> 키워드만으로 메소드를 자동 생성해줌


}
