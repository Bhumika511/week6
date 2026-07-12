package com.shopkart.api.model;

import java.util.List;

public record Order(
        long id,
        long orderId,
        long customerId,
        long cartId,
        String status,
        int totalPaise,
        String address,
        String createdAt,
        List<CartItem> items
) {
}
