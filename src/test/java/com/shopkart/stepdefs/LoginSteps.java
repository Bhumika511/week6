package com.shopkart.stepdefs;

import com.shopkart.support.BaseUiTest;
import com.shopkart.ui.pages.HomePage;
import com.shopkart.ui.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseUiTest {

    private LoginPage loginPage;
    private HomePage homePage;

    @Given("user opens ShopKart application")
    public void userOpensShopKartApplication() {

        loginPage = new LoginPage();
        loginPage.openLoginPage();

    }

    @When("user enters valid email and password")
    public void userEntersValidEmailAndPassword() {

        loginPage.enterEmail("alice@shopkart.test");
        loginPage.enterPassword("Shopkart_alice");

    }

    @When("user clicks Login button")
    public void userClicksLoginButton() {

        homePage = loginPage.clickLogin();

    }

    @Then("Home page should be displayed")
    public void homePageShouldBeDisplayed() {

        homePage.verifyHomePage();

    }
}