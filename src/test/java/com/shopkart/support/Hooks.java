package com.shopkart.support;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.shopkart.config.AppConfig;
import com.shopkart.data.secret.Secrets;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void beforeScenario() {
        LocalShopkartStub.ensureStarted();
        Configuration.baseUrl = AppConfig.BASE_URL;
        Configuration.browser = AppConfig.BROWSER;
        Configuration.timeout = AppConfig.TIMEOUT;
        Configuration.headless = Boolean.parseBoolean(Secrets.get("HEADLESS"));
        Configuration.browserSize = AppConfig.BROWSER_SIZE;
    }

    @After
    public void afterScenario() {
        Selenide.closeWebDriver();
    }
}
