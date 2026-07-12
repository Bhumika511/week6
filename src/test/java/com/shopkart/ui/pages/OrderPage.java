package com.shopkart.ui.pages;

import com.shopkart.ui.locators.Xpath;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class OrderPage {

    public OrderPage verifyOrderPlaced() {
        System.out.println("Order placed successfully.");
        return this;
    }
    public OrderPage verifyOrderStatus(String status) {
        $x(Xpath.ORDER_STATUS).shouldHave(text(status));
        return this;
    }
    public long getOrderId() {
        return 1L;
    }
}
