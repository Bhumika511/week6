package com.shopkart.stepdefs;

import com.shopkart.api.ProductClient;
import com.shopkart.api.model.Product;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductSearchSteps {

    private final ProductClient productClient = new ProductClient();
    private final WorldContext context;

    public ProductSearchSteps(WorldContext context) {
        this.context = context;
    }

    @When("{string} searches for {string}")
    public void userSearchesFor(String user, String productName) {

        context.getHomePage()
                .search(productName)
                .verifyProduct(productName);
    }

    @Then("the API should return {string}")
    public void apiShouldReturn(String productName) {

        List<Product> products = productClient.search(productName);

        assertFalse(products.isEmpty());

        assertTrue(products.stream()
                .anyMatch(product -> product.name().equals(productName)));
    }
}
