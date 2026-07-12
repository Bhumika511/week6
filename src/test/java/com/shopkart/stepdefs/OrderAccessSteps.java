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

    public OrderAccessSteps(WorldContext context) {
        this.context = context;
    }

    @Given("Alice has placed an order")
    public void aliceHasPlacedAnOrder() {

        LoginResponse alice = authClient.loginAsAlice();

        context.setToken(alice.token());
        context.setCustomerId(alice.customerId());

        Cart cart = cartClient.createCart(alice.token());
        context.setCartId(cart.cartId());

        cartClient.addItem(
                alice.token(),
                cart.cartId(),
                TestData.BAG_SKU,
                1
        );

        Order order = orderClient.placeOrder(
                alice.token(),
                cart.cartId(),
                TestData.DEFAULT_ADDRESS
        );

        context.setOrderId(order.id());
    }
    @When("Bob requests that order through the API")
    public void bobRequestsThatOrderThroughTheAPI() {

        LoginResponse bob = authClient.loginAsBob();

        int status = orderClient.getOrderStatusCode(
                bob.token(),
                context.getOrderId()
        );

        context.setResponseStatus(status);
    }
    @When("Alice cancels the order")
    public void aliceCancelsTheOrder() {

        orderClient.cancelOrder(
                context.getToken(),
                context.getOrderId()
        );
    }

    @Then("the order status should become {string}")
    public void orderStatusShouldBecome(String expectedStatus) {

        Order order = orderClient.getOrder(
                context.getToken(),
                context.getOrderId()
        );

        assertEquals(expectedStatus, order.status());
    }

    @When("Alice cancels the same order again")
    public void aliceCancelsTheSameOrderAgain() {

        int status = orderClient.cancelOrderStatusCode(
                context.getToken(),
                context.getOrderId()
        );

        context.setResponseStatus(status);
    }

    @Then("the response status should be {int}")
    public void responseStatusShouldBe(int expectedStatus) {

        assertEquals(expectedStatus, context.getResponseStatus());
    }
}
