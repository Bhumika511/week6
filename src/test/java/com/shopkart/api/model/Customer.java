package com.shopkart.api.model;

public record Customer(

        long id,
        String persona,
        String email,
        String displayName

) {
}