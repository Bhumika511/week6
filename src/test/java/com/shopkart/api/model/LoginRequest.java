package com.shopkart.api.model;

public record LoginRequest(
        String email,
        String password
) {
}
