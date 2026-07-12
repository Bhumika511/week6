package com.shopkart.ui.components;

import com.shopkart.ui.locators.Xpath;

import static com.codeborne.selenide.Selenide.$x;

public class Header {

    public Header openCart() {

        $x(Xpath.Cart).click();
        return this;
    }
}
