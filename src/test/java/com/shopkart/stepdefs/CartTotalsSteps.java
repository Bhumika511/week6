package com.shopkart.stepdefs;

import com.shopkart.api.CartClient;
import com.shopkart.api.model.Cart;
import com.shopkart.data.db.CartRepository;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTotalsSteps {

    private final WorldContext context;
    private final CartClient cartClient = new CartClient();
    private final CartRepository repository = new CartRepository();

    public CartTotalsSteps(WorldContext context) {
        this.context = context;
    }

    @Then("the API cart total should be {int}")
    public void verifyApiCartTotal(int expectedTotal) {
        Cart cart = cartClient.getCart(context.getToken(), context.getCartId());
        assertEquals(expectedTotal, cart.totalPaise());
    }

    @Then("the database cart total should be {int}")
    public void verifyDatabaseCartTotal(int expectedTotal) {
        assertEquals(expectedTotal, repository.cartTotal(context.getCartId()));
    }
}
