package edu.portfolio.coffeenew.controller;


import edu.portfolio.coffeenew.entity.Category;

public record CreateProductRequest
        (String productName, Category category, long price, String description) {
}
