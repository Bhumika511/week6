package com.shopkart.ui.pages;

import com.shopkart.ui.components.Header;
import com.shopkart.ui.components.ProductCard;
import com.shopkart.ui.locators.Xpath;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage {
    private final Header header = new Header();
    private final ProductCard productCard = new ProductCard();

    public HomePage search(String product) {
        productCard.searchProduct(product);
        return this;
    }

    public HomePage addProduct() {
        productCard.addToCart();
        return this;
    }

    public CartPage openCart() {
        header.openCart();
        return new CartPage();
    }

    public HomePage verifyHomePage() {
        $x(Xpath.SEARCH).shouldBe(visible);
        return this;
    }

    public boolean isDisplayed() {
        return $x(Xpath.SEARCH).isDisplayed();
    }
}
