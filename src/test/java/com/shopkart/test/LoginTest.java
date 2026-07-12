package com.shopkart.test;

import com.shopkart.support.BaseUiTest;
import com.shopkart.ui.pages.LoginPage;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseUiTest {

    @Test
    void shouldLoginSuccessfully() {

        new LoginPage()
                .openLoginPage()
                .login(
                        "alice@shopkart.test",
                        "Shopkart_alice"
                );

    }

}