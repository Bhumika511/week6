package com.shopkart.stepdefs;

import com.shopkart.api.OrderClient;
import com.shopkart.api.model.Order;
import com.shopkart.data.TestData;
import com.shopkart.data.db.OrderRepository;
import com.shopkart.support.WorldContext;
import com.shopkart.ui.pages.OrderPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutSteps {

    private final WorldContext context;
    private final OrderClient orderClient = new OrderClient();
    private final OrderRepository orderRepository = new OrderRepository();

    public CheckoutSteps(WorldContext context) {
        this.context = context;
    }

    @When("{string} enters the delivery address {string}")
    public void entersDeliveryAddress(String user, String address) {

        context.getCheckoutPage()
                .enterAddress(address);
    }

    @When("{string} places the order")
    public void placesTheOrder(String user) {

        OrderPage orderPage = context.getCheckoutPage()
                .placeOrder();

        context.setOrderPage(orderPage);
        context.setOrderId(orderPage.getOrderId());
    }

    @When("she checks out with a valid address")
    public void sheChecksOutWithAValidAddress() {

        context.setCheckoutPage(
                context.getCartPage().checkout()
        );

        context.getCheckoutPage()
                .enterAddress(TestData.DEFAULT_ADDRESS);

        OrderPage orderPage = context.getCheckoutPage()
                .placeOrder();

        context.setOrderPage(orderPage);
        context.setOrderId(orderPage.getOrderId());
    }

    @Then("the order confirmation shows status {string}")
    public void orderConfirmationShowsStatus(String status) {

        context.getOrderPage()
                .verifyOrderStatus(status);
    }

    @Then("GET order should return status {string}")
    public void getOrderShouldReturnStatus(String expectedStatus) {

        Order order = orderClient.getOrder(
                context.getToken(),
                context.getOrderId()
        );

        assertEquals(context.getOrderId(), order.id());
        assertEquals(expectedStatus, order.status());
    }

    @Then("database should contain one placed order")
    public void databaseShouldContainOnePlacedOrder() {

        assertTrue(
                orderRepository.orderExists(
                        context.getOrderId()
                )
        );

        assertEquals(
                "PLACED",
                orderRepository.orderStatus(
                        context.getOrderId()
                )
        );

        assertEquals(
                1,
                orderRepository.placedOrdersForCustomer(
                        context.getCustomerId()
                )
        );
    }
}