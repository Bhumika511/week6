package com.shopkart.data.db;

import com.shopkart.config.AppConfig;
import com.shopkart.data.secret.Secrets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private DatabaseConfig() {
    }

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                Secrets.get("DB_JDBC_URL"),
                Secrets.get("DB_USER"),
                Secrets.get("DB_PASSWORD")
        );
    }
}
