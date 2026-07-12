package com.shopkart.api;

import com.shopkart.api.base.BaseApiClient;
import com.shopkart.api.model.AddItemRequest;
import com.shopkart.api.model.Cart;

public class CartClient extends BaseApiClient {

        public Cart createCart(String token) {
                return authorized(token)
                                .when()
                                .post("/carts")
                                .then()
                                .spec(ApiSpec.createdResponse())
                                .extract()
                                .as(Cart.class);
        }

        public Cart addItem(String token, long cartId, String sku, int qty) {
                return authorized(token)
                                .body(new AddItemRequest(sku, qty))
                                .when()
                                .post("/carts/{id}/items", cartId)
                                .then()
                                .spec(ApiSpec.okResponse())
                                .extract()
                                .as(Cart.class);
        }

        public int addItemStatusCode(String token, long cartId, String sku, int qty) {
                return authorized(token)
                                .body(new AddItemRequest(sku, qty))
                                .when()
                                .post("/carts/{id}/items", cartId)
                                .then()
                                .extract()
                                .statusCode();
        }

    public Cart getCart(String token, long cartId) {
        return authorized(token)
                .when()
                .get("/carts/{id}", cartId)
                .then()
                .spec(ApiSpec.okResponse())
                .extract()
                .as(Cart.class);
    }
}
