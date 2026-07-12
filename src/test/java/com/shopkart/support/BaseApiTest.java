package com.shopkart.support;

import com.shopkart.api.Authclient;
import com.shopkart.api.model.LoginResponse;
import org.junit.jupiter.api.BeforeEach;

public class BaseApiTest {

    protected Authclient authClient;
    protected String token;

    @BeforeEach
    public void setup() {
        authClient = new Authclient();

        LoginResponse response = authClient.login(
                "alice@shopkart.test",
                "Shopkart_alice");

        token = response.token();
    }
}
