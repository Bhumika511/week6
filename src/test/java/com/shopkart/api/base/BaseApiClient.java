package com.shopkart.api.base;

import com.shopkart.api.ApiSpec;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseApiClient {

    protected RequestSpecification request() {

        return given().spec(ApiSpec.requestSpec()
        );
    }

    protected RequestSpecification authorized(String token) {
        return
                request().header("Authorization", "Bearer " + token);
    }
}
