package com.shopkart.data.db;

import com.shopkart.config.AppConfig;
import com.shopkart.data.secret.Secrets;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfig {

    private DatabaseConfig() {
    }

    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String host = Secrets.get("db.host");
            String port = Secrets.get("db.port");
            String database = Secrets.get("db.name");
            String user = Secrets.get("db.user");
            String password = Secrets.get("db.password");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {

            throw new RuntimeException("Database Connection Failed", e);

        }
    }
}
