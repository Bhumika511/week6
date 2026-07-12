package com.shopkart.api.model;

import java.util.List;

public record Cart(
        long id,
        long cartId,
        String status,
        List<CartItem> items,
        int totalPaise
) {
}
