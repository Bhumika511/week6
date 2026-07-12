package com.shopkart.config;

import com.shopkart.data.secret.Secrets;

public final class AppConfig {

    private AppConfig() {
    }

    public static final String BASE_URL = Secrets.get("SHOPKART_BASE_URL");

    public static final String API_BASE_URL = Secrets.get("SHOPKART_API_BASE_URL");

    public static final String BROWSER = Secrets.get("BROWSER");

    public static final String BROWSER_SIZE = Secrets.get("BROWSER_SIZE");

    public static final long TIMEOUT = Long.parseLong(
            Secrets.get("UI_TIMEOUT_MS"));
}
