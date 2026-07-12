package com.shopkart.stepdefs;

import com.shopkart.api.Authclient;
import com.shopkart.api.CartClient;
import com.shopkart.api.model.Cart;
import com.shopkart.api.model.LoginResponse;
import com.shopkart.support.WorldContext;
import com.shopkart.ui.pages.CartPage;
import com.shopkart.ui.pages.CheckoutPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.open;

public class CartSteps {

    private final WorldContext context;
    private final CartClient cartClient = new CartClient();

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

        cartClient.addItem(
                context.getToken(),
                cart.cartId(),
                sku,
                qty
        );

        open("/cart");

        context.setCartPage(new CartPage());
    }

    @When("she proceeds to checkout")
    public void sheProceedsToCheckout() {

        CartPage cartPage = context.getCartPage();

        if (cartPage == null) {
            cartPage = new CartPage();
            context.setCartPage(cartPage);
        }

        CheckoutPage checkoutPage = cartPage.checkout();
        context.setCheckoutPage(checkoutPage);
    }
}