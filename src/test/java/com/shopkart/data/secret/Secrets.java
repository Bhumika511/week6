package com.shopkart.data.secret;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;

public class Secrets {

    private Secrets() {
    }

    private static final Properties properties = new Properties();

    private static final Map<String, String> DEFAULTS = Map.ofEntries(
            Map.entry("SHOPKART_BASE_URL", "http://localhost:8080"),
            Map.entry("SHOPKART_API_BASE_URL", "http://localhost:8080/api"),
            Map.entry("BROWSER", "chrome"),
            Map.entry("BROWSER_SIZE", "1920x1080"),
            Map.entry("HEADLESS", "false"),
            Map.entry("UI_TIMEOUT_MS", "10000"),
            Map.entry("SHOPKART_ALICE_PASSWORD", "Shopkart_alice"),
            Map.entry("db.host", "localhost"),
            Map.entry("db.port", "3306"),
            Map.entry("db.name", "shopkart"),
            Map.entry("db.user", "shopkart_user"),
            Map.entry("db.password", "YourPassword@123"));

    static {
        loadFromClasspathResource("config/secrets-local.properties");
        loadFromFile(Path.of(".env"));
        loadFromFile(Path.of("src/test/resources/config/secrets-local.properties"));
    }

    private static void loadFromClasspathResource(String resourceName) {
        try (InputStream input = Secrets.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to load secrets from classpath resource: " + resourceName, e);
        }
    }

    private static void loadFromFile(Path filePath) {
        if (filePath == null || !Files.exists(filePath)) {
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                    continue;
                }
                int separator = trimmed.indexOf('=');
                if (separator <= 0) {
                    continue;
                }
                String key = trimmed.substring(0, separator).trim();
                String value = trimmed.substring(separator + 1).trim();
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    value = value.substring(1, value.length() - 1);
                }
                properties.setProperty(key, value);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to load secrets from file: " + filePath, e);
        }
    }

    public static String get(String key) {
        String env = System.getenv(key);
        if (env != null && !env.isBlank()) {
            return env;
        }

        String systemProperty = System.getProperty(key);
        if (systemProperty != null && !systemProperty.isBlank()) {
            return systemProperty;
        }

        String value = properties.getProperty(key);
        if (value != null && !value.isBlank()) {
            return value;
        }

        String defaultValue = DEFAULTS.get(key);
        if (defaultValue != null && !defaultValue.isBlank()) {
            return defaultValue;
        }

        throw new RuntimeException("Secret not found: " + key);
    }
}