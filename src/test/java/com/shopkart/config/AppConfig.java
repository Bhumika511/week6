package com.shopkart.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class AppConfig {

        private static final Properties properties = new Properties();

        static {

            String environment = Env.getEnvironment();

            String fileName = "config/application-" + environment + ".properties";

            try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream(fileName)) {

                if (input == null) {
                    throw new RuntimeException(fileName + " not found");
                }

                properties.load(input);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        public static String get(String key) {
            return properties.getProperty(key);
        }

    }
