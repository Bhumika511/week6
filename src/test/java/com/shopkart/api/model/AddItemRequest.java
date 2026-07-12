package com.shopkart.api.model;

public record AddItemRequest(
        String sku,
        int qty
) {
}
