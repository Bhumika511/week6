package com.shopkart.data.db;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseSupport {

    protected Connection connection() throws SQLException {
        return DatabaseConfig.getConnection();
    }
}
