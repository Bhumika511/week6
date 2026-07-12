package com.shopkart.api;

import com.shopkart.api.base.BaseApiClient;
import com.shopkart.api.model.Product;

import java.util.Arrays;
import java.util.List;

public class ProductClient extends BaseApiClient {

    public List<Product> search(String keyword) {
        return Arrays.asList(request()
                .queryParam("q", keyword)
                .when()
                .get("/products")
                .then()
                .spec(ApiSpec.okResponse())
                .extract()
                .as(Product[].class));
    }
}
