package com.shopkart.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class AuthClientTest {


        @Test
        void shouldLogin() {

            Authclient authClient = new Authclient();

            Response response = authClient.login(
                    "alice@shopkart.test",
                    "Shopkart_alice"
            );

            System.out.println(response.asPrettyString());

        }

    }
