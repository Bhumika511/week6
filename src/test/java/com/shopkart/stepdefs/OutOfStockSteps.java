package com.shopkart.stepdefs;

import com.shopkart.api.Authclient;
import com.shopkart.api.CartClient;
import com.shopkart.api.model.Cart;
import com.shopkart.api.model.LoginResponse;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class OutOfStockSteps {

    private final WorldContext context;
    private final Authclient authClient = new Authclient();
    private final CartClient cartClient = new CartClient();

    public OutOfStockSteps(WorldContext context) {
        this.context = context;
    }

    @Given("Alice has an empty cart")
    public void aliceHasAnEmptyCart() {
        LoginResponse alice = authClient.loginAsAlice();
        context.setToken(alice.token());

        Cart cart = cartClient.createCart(alice.token());
        context.setCartId(cart.cartId());
    }

    @When("she adds {int} of {string}")
    public void addTooManyItems(int qty, String sku) {
        context.setResponseStatus(cartClient.addItemStatusCode(context.getToken(), context.getCartId(), sku, qty));
    }
}
