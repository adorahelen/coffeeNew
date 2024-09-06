package edu.portfolio.coffeenew.repository;

import edu.portfolio.coffeenew.entity.Category;
import edu.portfolio.coffeenew.entity.Product;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


@Log4j2
@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    // -> 따라서 한꺼번에 돌릴 떄 문제가 없었던 것은 위에서 테스트 코드 인스턴스 생명 주기 조정 & 필드 지정 역시 올바르게 되었다.
    // 코드에 문제가 없다는 것을 전제로 (클론 코딩)
    // @Before @After 로 적용시킨, 내장 임베디드 mySql 이 아닌 실 디비를 연결한 점이 달라졌고
    @Test
    public void testInsert() {

        Product product = new Product();
        product.setProductName("Mocha");
        product.setCategory(Category.COFFEE_BEAN_PACKAGE);
        product.setPrice(6000L);
        product.setDescription("Chocolate flavored coffee");
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);

        assertThat(savedProduct).isNotNull();

        assertThat(savedProduct.getProductId()).isNotNull();
        assertThat(savedProduct.getProductName()).isEqualTo("Mocha");
        assertThat(savedProduct.getCategory()).isEqualTo(Category.COFFEE_BEAN_PACKAGE);
        assertThat(savedProduct.getPrice()).isEqualTo(6000L);
        assertThat(savedProduct.getDescription()).isEqualTo("Chocolate flavored coffee");
    }
    @Test
    public void testInsert2() {
        Product product = Product.builder()
                .productName("Mocha2")
                .category(Category.COFFEE_BEAN_PACKAGE)
                .price(7000L)
                .description("chocolate and coffee")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product savedProduct = productRepository.save(product);
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getProductName()).isEqualTo("Mocha2");
        assertThat(savedProduct.getCategory()).isEqualTo(Category.COFFEE_BEAN_PACKAGE);
        assertThat(savedProduct.getPrice()).isEqualTo(7000L);
        assertThat(savedProduct.getDescription()).isEqualTo("chocolate and coffee");
    }
}