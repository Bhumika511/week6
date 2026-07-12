package com.shopkart.api;

import com.shopkart.api.base.BaseApiClient;
import com.shopkart.api.model.LoginRequest;
import com.shopkart.api.model.LoginResponse;
import com.shopkart.data.TestUsers;
import com.shopkart.data.secret.Secrets;

public class Authclient extends BaseApiClient {

    public LoginResponse login(String email, String password) {
        return request()
                .body(new LoginRequest(email, password))
                .when()
                .post("/auth/login")
                .then()
                .spec(ApiSpec.okResponse())
                .extract()
                .as(LoginResponse.class);
    }

    public LoginResponse loginAsAlice() {
        return login(TestUsers.ALICE_EMAIL, Secrets.get("SHOPKART_ALICE_PASSWORD"));
    }

    public LoginResponse loginAsBob() {
        return login(TestUsers.BOB_EMAIL, Secrets.get("SHOPKART_BOB_PASSWORD"));
    }

    public LoginResponse loginAsCarol() {
        return login(TestUsers.CAROL_EMAIL, Secrets.get("SHOPKART_CAROL_PASSWORD"));
    }
}
