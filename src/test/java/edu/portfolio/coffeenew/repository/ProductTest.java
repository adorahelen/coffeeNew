package edu.portfolio.coffeenew.repository;

import edu.portfolio.coffeenew.entity.Category;
import edu.portfolio.coffeenew.entity.Product;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    private Product newProduct;

    @BeforeAll
    public void setUp() {
        Product product = new Product();
        product.setProductName("test");
        product.setCategory(Category.COFFEE_BEAN_PACKAGE);
        product.setPrice(1000L);
        product.setDescription("testIsStillHardTome");
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        newProduct = productRepository.save(product);
    }

    @Test
    @Order(1)
    public void testInsert() {
        log.info("Inserting new product");
        long id = productRepository.count();
        log.info("Inserted new product with id: " + id);
        assertThat(newProduct.getProductName(), is("test"));
        assertThat(newProduct.getCategory(), is(Category.COFFEE_BEAN_PACKAGE));
        assertThat(newProduct.getPrice(), is(1000L));
    }

    @Test
    @Order(2)
    public void testFindById() {
        var product = productRepository.findById(newProduct.getProductId());
        assertThat(product.isPresent(), is(true));
    }

    @Test
    @Order(3)
    public void testFindByCategory() {
        var products = productRepository.findByCategory(Category.COFFEE_BEAN_PACKAGE);
        assertThat(products.isEmpty(), is(false));
    }

    @Test
    @Order(4)
    public void testFindByName() {
        var products = productRepository.findByProductName("test");
        assertThat(products.isEmpty(), is(false));
    }

    @Test
    @Order(5)
    public void testUpdate() {
        newProduct.setProductName("New");
        productRepository.save(newProduct);
        log.info("Updated Product: {}", newProduct);

        assertThat(productRepository.findById(newProduct.getProductId()).isPresent(), is(true));

    }

    @Test
    @Order(6)
    public void testDelete() {
        productRepository.deleteById(newProduct.getProductId());
        assertThat(productRepository.findById(newProduct.getProductId()).isPresent(), is(false));
    }

    @AfterAll
    public void cleanUp() {
        productRepository.deleteAll();
    }
}