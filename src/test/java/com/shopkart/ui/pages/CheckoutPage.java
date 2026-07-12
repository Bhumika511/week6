package com.shopkart.ui.pages;

import com.shopkart.ui.locators.Xpath;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class CheckoutPage {
    public CheckoutPage enterAddress(String address) {
        $x(Xpath.ADDRESS)
                .shouldBe(visible)
                .setValue(address);

        return this;
    }

    public OrderPage placeOrder() {
        $x(Xpath.PLACE_ORDER).click();

        return new OrderPage();
    }
}
