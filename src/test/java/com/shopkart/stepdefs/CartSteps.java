package com.shopkart.stepdefs;

import com.shopkart.api.Authclient;
import com.shopkart.api.CartClient;
import com.shopkart.api.OrderClient;
import com.shopkart.api.model.Cart;
import com.shopkart.api.model.LoginResponse;
import com.shopkart.api.model.Order;
import com.shopkart.data.TestData;
import com.shopkart.data.db.OrderRepository;
import com.shopkart.support.WorldContext;
import com.shopkart.ui.pages.CartPage;
import com.shopkart.ui.pages.CheckoutPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartSteps {

    private final WorldContext context;
    private final CartClient cartClient = new CartClient();
    private final OrderClient orderClient = new OrderClient();
    private final OrderRepository orderRepository = new OrderRepository();

    public CartSteps(WorldContext context) {
        this.context = context;
    }

    @Given("{string} is logged in")
    public void isLoggedIn(String user) {
        Authclient authClient = new Authclient();
        LoginResponse login = authClient.loginAsAlice();
        context.setToken(login.token());
        context.setCustomerId(login.customerId());
    }

    @When("she adds {int} x {string} to her cart")
    public void sheAddsItemsToHerCart(int qty, String sku) {
        Cart cart = cartClient.createCart(context.getToken());
        context.setCartId(cart.cartId());
        cartClient.addItem(context.getToken(), cart.cartId(), sku, qty);
    }

    @When("she checks out with a valid address")
    public void sheChecksOutWithAValidAddress() {
        CartPage cartPage = context.getCartPage();
        if (cartPage == null) {
            cartPage = new CartPage();
            context.setCartPage(cartPage);
        }

        CheckoutPage checkoutPage = cartPage.checkout();
        context.setCheckoutPage(checkoutPage);
        context.setOrderPage(checkoutPage.placeOrder());
    }

    @Then("the order confirmation shows status {string}")
    public void orderConfirmationShowsStatus(String status) {
        Order order = orderClient.placeOrder(context.getToken(), context.getCartId(), TestData.DEFAULT_ADDRESS);
        context.setOrderId(order.id());
        assertEquals(status, order.status());
    }

    @Then("GET order should return status {string}")
    public void getOrderShouldReturnStatus(String status) {
        Order order = orderClient.getOrder(context.getToken(), context.getOrderId());
        assertEquals(status, order.status());
    }

    @Then("database should contain one placed order")
    public void databaseShouldContainOnePlacedOrder() {
        assertTrue(orderRepository.orderExists(context.getOrderId()));
        assertEquals("PLACED", orderRepository.orderStatus(context.getOrderId()));
        assertEquals(1, orderRepository.placedOrdersForCustomer(context.getCustomerId()));
    }
}
