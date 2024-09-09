package edu.portfolio.coffeenew.controller;

import java.util.List;

public record CreateOrderRequest(
        String email,
        String address,
        String postcode,
        List<OrderItemRequest> orderItems
) {
}