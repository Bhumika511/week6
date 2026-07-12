package com.shopkart.api;

import com.shopkart.config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CartClient {


        public CartClient() {
            RestAssured.baseURI = AppConfig.get("api.url");
        }

        public Response createCart(String token) {

            return given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .post("/carts");

        }

        public Response getCart(String token, int cartId) {

            return given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("/carts/" + cartId);

        }

    public Response addItem(String token,
                            int cartId,
                            String sku,
                            int qty) {

        return given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(Map.of(
                        "sku", sku,
                        "qty", qty
                ))
                .when()
                .post("/carts/" + cartId + "/items");
    }

    }
