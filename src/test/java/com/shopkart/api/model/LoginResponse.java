package com.shopkart.api.model;

public record LoginResponse(
        String token,
        long customerId
) {
}
