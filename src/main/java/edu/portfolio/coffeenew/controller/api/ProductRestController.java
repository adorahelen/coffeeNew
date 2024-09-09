package edu.portfolio.coffeenew.controller.api;


import edu.portfolio.coffeenew.entity.Category;
import edu.portfolio.coffeenew.entity.Product;
import edu.portfolio.coffeenew.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductRestController {

    private final ProductService productService;
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/api/v1/products")
    public List<Product> getAllProducts(@RequestParam Optional<Category> category) {

        return category.map(productService::getProductsByCategory)
                .orElse(productService.getAllProducts());
      //  return productService.getAllProducts();
    }
}
