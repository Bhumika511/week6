package com.shopkart.stepdefs;

import com.shopkart.api.Authclient;
import com.shopkart.api.CartClient;
import com.shopkart.api.OrderClient;
import com.shopkart.api.model.Cart;
import com.shopkart.api.model.LoginResponse;
import com.shopkart.api.model.Order;
import com.shopkart.data.TestData;
import com.shopkart.support.WorldContext;
import com.shopkart.ui.pages.HomePage;
import com.shopkart.ui.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderSteps {

    private final WorldContext context;
    private final Authclient authClient = new Authclient();
    private final CartClient cartClient = new CartClient();
    private final OrderClient orderClient = new OrderClient();

    public OrderSteps(WorldContext context) {
        this.context = context;
    }

    @Given("User opens ShopKart")
    public void userOpensShopKart() {
        LoginPage loginPage = new LoginPage();
        loginPage.openLoginPage();
        context.setLoginPage(loginPage);
    }

    @Given("User logs in")
    public void userLogsIn() {
        LoginResponse login = authClient.loginAsAlice();
        context.setToken(login.token());
        context.setCustomerId(login.customerId());

        HomePage homePage = new LoginPage()
                .openLoginPage()
                .login("alice@shopkart.test", "Shopkart_alice");
        context.setHomePage(homePage);
    }

    @When("User searches for {string}")
    public void userSearches(String product) {
        context.getHomePage().search(product);
    }

    @When("User adds the product to cart")
    public void userAddsProduct() {
        Cart cart = cartClient.createCart(context.getToken());
        context.setCartId(cart.cartId());
        cartClient.addItem(context.getToken(), cart.cartId(), TestData.BAG_SKU, 1);
    }

    @When("User opens cart")
    public void userOpensCart() {
        context.setCartPage(context.getHomePage().openCart());
    }

    @When("User proceeds to checkout")
    public void userCheckout() {
        context.setCheckoutPage(context.getCartPage().checkout());
        context.setOrderPage(context.getCheckoutPage().placeOrder());
    }

    @Then("Order should be placed successfully")
    public void orderPlaced() {
        Order order = orderClient.placeOrder(context.getToken(), context.getCartId(), TestData.DEFAULT_ADDRESS);
        context.setOrderId(order.id());
        assertNotNull(order);
        assertEquals("PLACED", order.status());
    }
}
