package com.shopkart.stepdefs;

import com.shopkart.api.Authclient;
import com.shopkart.api.model.LoginResponse;
import com.shopkart.data.TestUsers;
import com.shopkart.data.secret.Secrets;
import com.shopkart.support.BaseUiTest;
import com.shopkart.support.WorldContext;
import com.shopkart.ui.pages.HomePage;
import com.shopkart.ui.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class LoginSteps extends BaseUiTest {

    private final Authclient authClient = new Authclient();
    private final WorldContext context;

    private LoginPage loginPage;
    private HomePage homePage;

    public LoginSteps(WorldContext context) {
        this.context = context;
    }

    @Given("{string} opens the ShopKart login page")
    public void userOpensShopKartApplication(String user) {
        loginPage = new LoginPage();
        loginPage.openLoginPage();
        context.setLoginPage(loginPage);
    }

    @When("{string} logs in")
    public void userEntersValidEmailAndPassword(String user) {
        Credentials credentials = credentialsFor(user);

        LoginResponse login = authClient.login(credentials.email(), credentials.password());
        context.setToken(login.token());
        context.setCustomerId(login.customerId());

        homePage = loginPage.login(credentials.email(), credentials.password());
        context.setHomePage(homePage);
    }

    @Then("she should be redirected to the home page")
    public void homePageShouldBeDisplayed() {
        Assertions.assertTrue(context.getHomePage().isDisplayed());
    }

    private Credentials credentialsFor(String user) {
        return switch (user.toLowerCase()) {
            case "alice" -> new Credentials(TestUsers.ALICE_EMAIL, Secrets.get("SHOPKART_ALICE_PASSWORD"));
            case "bob" -> new Credentials(TestUsers.BOB_EMAIL, Secrets.get("SHOPKART_BOB_PASSWORD"));
            case "carol" -> new Credentials(TestUsers.CAROL_EMAIL, Secrets.get("SHOPKART_CAROL_PASSWORD"));
            default -> throw new IllegalArgumentException("Unknown user: " + user);
        };
    }

    private record Credentials(String email, String password) {
    }
}
