package com.shopkart.config;

public class Env {
        public static String getEnvironment() {
            return System.getProperty("env", "local");
        }

    }
