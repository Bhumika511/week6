package com.shopkart.data.secret;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Secrets {

    private Secrets() {
    }

    private static final Properties properties = new Properties();

    static {

        try (InputStream input = Secrets.class.getClassLoader()
                .getResourceAsStream("config/secrets-local.properties")) {

            if (input == null) {
                throw new RuntimeException("Secrets file not found.");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String get(String key) {

        String env = System.getenv(key);

        if (env != null && !env.isBlank()) {
            return env;
        }

        String value = properties.getProperty(key);

        if (value == null || value.isBlank()) {
            throw new RuntimeException("Secret not found: " + key);
        }

        return value;
    }
}