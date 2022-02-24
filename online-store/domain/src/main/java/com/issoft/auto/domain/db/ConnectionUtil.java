package com.issoft.auto.domain.db;

import java.sql.*;

public class ConnectionUtil {
    private static final String URL = "db.url";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static String url = PropertiesUtil.get(URL);
    private static String user = PropertiesUtil.get(USER);
    private static String password = PropertiesUtil.get(PASSWORD);

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

}
