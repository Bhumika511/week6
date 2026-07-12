package com.shopkart.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductClinetTest {


        ProductClient productClient = new ProductClient();

        @Test
        void shouldGetAllProducts() {

            Response response = productClient.getAllProducts();

            response.prettyPrint();

            assertEquals(200, response.statusCode());

        }

        @Test
        void shouldSearchProducts() {

            Response response = productClient.searchProducts("bag");

            response.prettyPrint();

            assertEquals(200, response.statusCode());

        }

    @Test
    void shouldGetProductBySku() {

        Response response = productClient.getProduct("SKU-BAG");

        response.then()
                .statusCode(200);
        assertEquals(200, response.statusCode());

        assertEquals(
                "SKU-BAG",
                response.jsonPath().getString("sku")
        );

        assertTrue(
                response.jsonPath().getString("name").length() > 0
        );

        assertTrue(
                response.jsonPath().getInt("pricePaise") > 0
        );

    }

    }
