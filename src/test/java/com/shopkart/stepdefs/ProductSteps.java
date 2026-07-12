package com.shopkart.stepdefs;

import com.shopkart.api.ProductClient;
import com.shopkart.api.model.Product;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductSteps {

    private final ProductClient productClient = new ProductClient();

    @When("user searches for {string}")
    public void userSearchesFor(String keyword) {
        List<Product> products = productClient.search(keyword);
        assertFalse(products.isEmpty());
        assertTrue(products.stream().anyMatch(product -> product.name().contains(keyword)));
    }

    @Then("the product list should contain {string}")
    public void productListShouldContain(String productName) {
        List<Product> products = productClient.search(productName);
        assertTrue(products.stream().anyMatch(product -> product.name().equals(productName)));
    }
}
