package com.issoft.auto.domain.db;

import java.sql.*;

public class ConnectionUtil {

    public static Connection getConnection(String serverUrl, String userName, String password) {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(serverUrl, userName, password);
        } catch (SQLException | ClassNotFoundException throwables) {
            //DBManager.closeConnectionAndAddNull();
            throwables.printStackTrace();
        }
        return connection;
    }

}
