package com.shopkart.api;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class Authclient extends ApiClient  {


        public Response login(String email, String password) {

            Map<String, String> request = new HashMap<>();

            request.put("email", email);
            request.put("password", password);

            return request()
                    .body(request)
                    .post("/auth/login");

        }

    }
