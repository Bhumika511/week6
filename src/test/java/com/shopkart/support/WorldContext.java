package com.shopkart.support;

import com.shopkart.ui.pages.CartPage;
import com.shopkart.ui.pages.CheckoutPage;
import com.shopkart.ui.pages.HomePage;
import com.shopkart.ui.pages.LoginPage;
import com.shopkart.ui.pages.OrderPage;

public class WorldContext {

    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private OrderPage orderPage;

    private String token;
    private long customerId;
    private long cartId;
    private long orderId;
    private int responseStatus;

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    public CartPage getCartPage() {
        return cartPage;
    }

    public void setCartPage(CartPage cartPage) {
        this.cartPage = cartPage;
    }

    public CheckoutPage getCheckoutPage() {
        return checkoutPage;
    }

    public void setCheckoutPage(CheckoutPage checkoutPage) {
        this.checkoutPage = checkoutPage;
    }

    public OrderPage getOrderPage() {
        return orderPage;
    }

    public void setOrderPage(OrderPage orderPage) {
        this.orderPage = orderPage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }
}
