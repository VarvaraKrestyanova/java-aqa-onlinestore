package com.issoft.auto.domain.db;

import java.sql.*;

public class ConnectionUtil {
    private static final String URL = "db.url";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(PropertiesUtil.get(URL), PropertiesUtil.get(USER), PropertiesUtil.get(PASSWORD));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

}
