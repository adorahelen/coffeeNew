package edu.portfolio.coffeenew.service;


import edu.portfolio.coffeenew.entity.Category;
import edu.portfolio.coffeenew.entity.Product;
import edu.portfolio.coffeenew.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(String productName, Category category, long price, String description) {
        var product = new Product(null, productName, category, price, description, LocalDateTime.now(), LocalDateTime.now());
        return productRepository.save(product);
    }

}

