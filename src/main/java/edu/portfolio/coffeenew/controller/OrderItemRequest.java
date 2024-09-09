package edu.portfolio.coffeenew.controller;

import edu.portfolio.coffeenew.entity.Category;

public class OrderItemRequest {

        private Long productId;
        private Category category;
        private Long price;
        private int quantity;

        // Getters and Setters

        public Long getProductId() {
                return productId;
        }

        public void setProductId(Long productId) {
                this.productId = productId;
        }

        public Category getCategory() {
                return category;
        }

        public void setCategory(Category category) {
                this.category = category;
        }

        public Long getPrice() {
                return price;
        }

        public void setPrice(Long price) {
                this.price = price;
        }

        public int getQuantity() {
                return quantity;
        }

        public void setQuantity(int quantity) {
                this.quantity = quantity;
        }
}