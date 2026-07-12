package com.shopkart.ui.pages;

import com.shopkart.ui.locators.Xpath;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CheckoutPage {
    public CheckoutPage enterAddress(String address) {

        $x(Xpath.ADDRESS).setValue(address);

        return this;

    }

    public OrderPage placeOrder() {

        $x(Xpath.PLACE_ORDER).click();

        return new OrderPage();

    }
}
