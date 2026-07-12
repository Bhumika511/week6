package com.shopkart.ui.components;

import com.shopkart.ui.locators.Xpath;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProductCard {
    public ProductCard searchProduct(String product) {

        $x(Xpath.SEARCH).setValue(product);
        $x(Xpath.SEARCH_BUTTON).click();
        return this;
    }
    public ProductCard verifyProduct(String productName) {

        $x(Xpath.productName(productName)).shouldBe(visible);
        return this;
    }
    public ProductCard addToCart() {

        $x(Xpath.ADDTOCART).click();

        return this;
    }

}
