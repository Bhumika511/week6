package com.shopkart.stepdefs;

import com.shopkart.api.Authclient;
import com.shopkart.api.CartClient;
import com.shopkart.api.OrderClient;
import com.shopkart.api.model.Cart;
import com.shopkart.api.model.LoginResponse;
import com.shopkart.api.model.Order;
import com.shopkart.data.TestData;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderAccessSteps {

    private final WorldContext context;
    private final Authclient authClient = new Authclient();
    private final CartClient cartClient = new CartClient();
    private final OrderClient orderClient = new OrderClient();
    private int statusCode;

    public OrderAccessSteps(WorldContext context) {
        this.context = context;
    }

    @Given("Alice has placed an order")
    public void aliceHasPlacedAnOrder() {
        LoginResponse alice = authClient.loginAsAlice();
        context.setToken(alice.token());
        context.setCustomerId(alice.customerId());

        Cart cart = cartClient.createCart(alice.token());
        cartClient.addItem(alice.token(), cart.cartId(), TestData.BAG_SKU, 1);

        Order order = orderClient.placeOrder(alice.token(), cart.cartId(), TestData.DEFAULT_ADDRESS);
        context.setOrderId(order.id());
    }

    @When("Bob requests that order through the API")
    public void bobRequestsOrder() {
        LoginResponse bob = authClient.loginAsBob();

        context.setResponseStatus(
                orderClient.getOrderStatusCode(
                        bob.token(),
                        context.getOrderId()
                )
        );
    }

    @Then("the response status should be {int}")
    public void verifyStatus(int expectedStatus) {
        assertEquals(expectedStatus, context.getResponseStatus());
    }
}
