package com.shopkart.ui.pages;

import com.shopkart.ui.locators.Xpath;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage {

    public CartPage verifyCartTotal(String total) {
        $x(Xpath.CART_TOTAL).shouldHave(text(total));
        return this;
    }

    public CheckoutPage checkout() {
        return new CheckoutPage();
    }
}
