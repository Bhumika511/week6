package com.shopkart.stepdefs;

import com.shopkart.api.ProductClient;
import com.shopkart.api.model.Product;
import io.cucumber.java.en.Then;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductSearchSteps {

    private final ProductClient productClient = new ProductClient();

    @Then("the API should return {string}")
    public void apiShouldReturn(String productName) {
        List<Product> products = productClient.search(productName);
        assertFalse(products.isEmpty());
        assertTrue(products.stream().anyMatch(product -> product.name().equals(productName)));
    }
}
