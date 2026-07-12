package com.shopkart.api;

import com.shopkart.config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class OrderClient {


        public OrderClient() {

            RestAssured.baseURI =
                    AppConfig.get("api.url");

        }

    public Response createOrder(String token, int cartId) {

        return given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "cartId", cartId,
                        "address", "123 MG Road, Bangalore, Karnataka"
                ))
                .when()
                .post("/orders");
    }

        public Response getOrder(String token,
                                 int orderId) {

            return given()
                    .header("Authorization",
                            "Bearer " + token)
                    .when()
                    .get("/orders/" + orderId);

        }

        public Response cancelOrder(String token,
                                    int orderId) {

            return given()
                    .header("Authorization",
                            "Bearer " + token)
                    .when()
                    .post("/orders/" + orderId + "/cancel");

        }

    }
