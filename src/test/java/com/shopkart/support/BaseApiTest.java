package com.shopkart.support;

import com.shopkart.api.Authclient;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;

public class BaseApiTest {


    protected Authclient authClient;
    protected String token;

    @BeforeEach
    public void setup() {

        authClient = new Authclient();

        Response response = authClient.login(
                "alice@shopkart.test",
                "Shopkart_alice"
        );

        token = response.jsonPath().getString("token");
    }
    }
