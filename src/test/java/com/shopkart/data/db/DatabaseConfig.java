package com.shopkart.data.db;

import com.shopkart.config.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfig {

    private DatabaseConfig() {
    }

    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String host = AppConfig.get("db.host");
            String port = AppConfig.get("db.port");
            String database = AppConfig.get("db.name");
            String user = AppConfig.get("db.user");
            String password = AppConfig.get("db.password");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {

            throw new RuntimeException("Database Connection Failed", e);

        }
    }
}
