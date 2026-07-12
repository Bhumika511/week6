package com.shopkart.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartClientTest {


        Authclient authClient = new Authclient();
        CartClient cartClient = new CartClient();

        @Test
        void shouldCreateCart() {

            Response login =
                    authClient.login(
                            "alice@shopkart.test",
                            "Shopkart_alice"
                    );

            String token =
                    login.jsonPath().getString("token");

            Response cart =
                    cartClient.createCart(token);

            cart.prettyPrint();

            assertEquals(201, cart.statusCode());

        }

    @Test
    void shouldAddItemToCart() {

        Response login =
                authClient.login(
                        "alice@shopkart.test",
                        "Shopkart_alice"
                );

        String token =
                login.jsonPath().getString("token");

        Response createCart =
                cartClient.createCart(token);

        int cartId =
                createCart.jsonPath().getInt("id");

        Response addItem =
                cartClient.addItem(
                        token,
                        cartId,
                        "SKU-BAG",
                        2
                );

        addItem.prettyPrint();

        assertEquals(200, addItem.statusCode());

        assertEquals(
                99800,
                addItem.jsonPath().getInt("totalPaise")
        );

        assertEquals(
                "SKU-BAG",
                addItem.jsonPath().getString("items[0].sku")
        );

        assertEquals(
                2,
                addItem.jsonPath().getInt("items[0].qty")
        );
    }

    }
