package com.shopkart.support;

import com.codeborne.selenide.Configuration;
import com.shopkart.config.AppConfig;
import org.junit.jupiter.api.BeforeEach;

public class BaseUiTest {
    @BeforeEach
    public void setup() {

        Configuration.browser = "chrome";
        Configuration.baseUrl = AppConfig.get("app.url");
        System.out.println(Configuration.baseUrl);
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;

    }
}
