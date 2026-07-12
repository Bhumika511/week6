package com.shopkart.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderClinetTest {


        Authclient authClient = new Authclient();
        CartClient cartClient = new CartClient();
        OrderClient orderClient = new OrderClient();

        @Test
        void shouldCreateOrder() {

            Response login =
                    authClient.login(
                            "alice@shopkart.test",
                            "Shopkart_alice"
                    );

            String token =
                    login.jsonPath().getString("token");

            Response cart =
                    cartClient.createCart(token);

            int cartId =
                    cart.jsonPath().getInt("id");

            cartClient.addItem(
                    token,
                    cartId,
                    "SKU-BAG",
                    2
            );

            Response order =
                    orderClient.createOrder(
                            token,
                            cartId
                    );

            order.prettyPrint();

            assertEquals(201, order.statusCode());

        }

        @Test
        void shouldGetOrder() {

            Response login =
                    authClient.login(
                            "alice@shopkart.test",
                            "Shopkart_alice"
                    );

            String token =
                    login.jsonPath().getString("token");

            Response cart =
                    cartClient.createCart(token);

            int cartId =
                    cart.jsonPath().getInt("id");

            cartClient.addItem(
                    token,
                    cartId,
                    "SKU-BAG",
                    1
            );

            Response createOrder =
                    orderClient.createOrder(
                            token,
                            cartId
                    );

            int orderId =
                    createOrder.jsonPath().getInt("id");

            Response getOrder =
                    orderClient.getOrder(
                            token,
                            orderId
                    );

            getOrder.prettyPrint();

            assertEquals(200, getOrder.statusCode());

        }

        @Test
        void shouldCancelOrder() {

            Response login =
                    authClient.login(
                            "alice@shopkart.test",
                            "Shopkart_alice"
                    );

            String token =
                    login.jsonPath().getString("token");

            Response cart =
                    cartClient.createCart(token);

            int cartId =
                    cart.jsonPath().getInt("id");

            cartClient.addItem(
                    token,
                    cartId,
                    "SKU-BAG",
                    1
            );

            Response createOrder =
                    orderClient.createOrder(
                            token,
                            cartId
                    );

            int orderId =
                    createOrder.jsonPath().getInt("id");

            Response cancel =
                    orderClient.cancelOrder(
                            token,
                            orderId
                    );

            cancel.prettyPrint();

            assertEquals(200, cancel.statusCode());

        }

    }
