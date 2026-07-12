package com.shopkart.api.model;

public record CartItem(
        String sku,
        String name,
        int qty,
        int unitPricePaise,
        int lineTotalPaise
) {
}