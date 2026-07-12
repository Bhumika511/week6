package com.shopkart.api.model;

public record PlaceOrderRequest(
        long cartId,
        String address
) {
}
