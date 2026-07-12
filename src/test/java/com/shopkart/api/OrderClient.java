package com.shopkart.api;

import com.shopkart.api.base.BaseApiClient;
import com.shopkart.api.model.Order;
import com.shopkart.api.model.PlaceOrderRequest;

public class OrderClient extends BaseApiClient {

        public Order placeOrder(String token, long cartId, String address) {
                return authorized(token)
                                .body(new PlaceOrderRequest(cartId, address))
                                .when()
                                .post("/orders")
                                .then()
                                .spec(ApiSpec.createdResponse())
                                .extract()
                                .as(Order.class);
        }

        public Order getOrder(String token, long orderId) {
                return authorized(token)
                                .when()
                                .get("/orders/{id}", orderId)
                                .then()
                                .spec(ApiSpec.okResponse())
                                .extract()
                                .as(Order.class);
        }

        public int getOrderStatusCode(String token, long orderId) {
                return authorized(token)
                                .when()
                                .get("/orders/{id}", orderId)
                                .then()
                                .extract()
                                .statusCode();
        }

        public Order cancelOrder(String token, long orderId) {
                return authorized(token)
                                .when()
                                .post("/orders/{id}/cancel", orderId)
                                .then()
                                .spec(ApiSpec.okResponse())
                                .extract()
                                .as(Order.class);
        }

        public int cancelOrderStatusCode(String token, long orderId) {
                return authorized(token)
                                .when()
                                .post("/orders/{id}/cancel", orderId)
                                .then()
                                .extract()
                                .statusCode();
        }
}
