package edu.portfolio.coffeenew.controller;

import java.util.List;

public record CreateOrderRequest(
        String email,
        String address,
        String postcode,
        List<OrderItemRequest> orderItems
) {
}
// 컨트롤러 단을 위한 디티오