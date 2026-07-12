package com.shopkart.support;

import com.codeborne.selenide.Configuration;
import com.shopkart.config.AppConfig;
import com.shopkart.data.secret.Secrets;
import org.junit.jupiter.api.BeforeEach;

public class BaseUiTest {
    @BeforeEach
    public void setup() {
        Configuration.browser = AppConfig.BROWSER;
        Configuration.baseUrl = AppConfig.BASE_URL;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.headless = Boolean.parseBoolean(Secrets.get("headless"));
    }
}
