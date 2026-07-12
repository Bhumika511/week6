package com.shopkart.api.model;

public record Product(
        String sku,
        String name,
        String description,
        String category,
        int pricePaise,
        int stock,
        String imageKey
) {
}
