package com.shopkart.api;

import com.shopkart.config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductClient {


        public ProductClient() {

            RestAssured.baseURI = AppConfig.get("api.url");

        }

        public Response getAllProducts() {

            return given()
                    .when()
                    .get("/products");

        }

        public Response searchProducts(String keyword) {

            return given()
                    .queryParam("q", keyword)
                    .when()
                    .get("/products");

        }

        public Response getProduct(String sku) {

            return given()
                    .when()
                    .get("/products/" + sku);

        }

    }
