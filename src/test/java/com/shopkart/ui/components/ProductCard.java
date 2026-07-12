package com.shopkart.ui.components;

import com.shopkart.ui.locators.Xpath;

import static com.codeborne.selenide.Selenide.$x;

public class ProductCard {
    public ProductCard searchProduct(String product) {

        $x(Xpath.SEARCH).setValue(product);

        return this;
    }

    public ProductCard addToCart() {

        $x(Xpath.ADDTOCART).click();

        return this;
    }

}
