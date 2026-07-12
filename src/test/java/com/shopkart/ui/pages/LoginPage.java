package com.shopkart.ui.pages;

import com.codeborne.selenide.WebDriverRunner;
import com.shopkart.ui.locators.Xpath;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    public LoginPage openLoginPage() {
        open("/login");
        return this;
    }

    public LoginPage enterEmail(String email) {
        $x(Xpath.EMAIL).setValue(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        $x(Xpath.PASSWORD).setValue(password);
        return this;
    }

    public HomePage clickLogin() {
        $x(Xpath.LOGIN_BUTTON).click();
        return new HomePage();
    }

    public HomePage login(String email, String password) {
        return openLoginPage()
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin();

    }
}
