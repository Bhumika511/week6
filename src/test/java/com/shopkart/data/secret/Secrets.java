package com.shopkart.data.secret;

import com.shopkart.config.Env;

public class Secrets {
    private Secrets() {
    }

    public static String get(String key) {
        return Env.get(key);
    }
}