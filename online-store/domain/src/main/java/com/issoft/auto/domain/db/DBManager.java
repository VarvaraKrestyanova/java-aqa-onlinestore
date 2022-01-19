package com.issoft.auto.domain.db;

import com.issoft.auto.domain.Category;
import com.issoft.auto.domain.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static final String url = "jdbc:h2:~/OnlineStoreData";
    private static final String user = "****";
    private static final String password = "****";

    public static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        try {
            Connection connection = ConnectionUtil.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORY;");
            while (resultSet.next()){
                categories.add(new Category(resultSet.getInt("ID"), resultSet.getString("NAME")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return categories;
    }

    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try {
            Connection connection = ConnectionUtil.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT;");
            while (resultSet.next()){
                products.add(new Product
                        (resultSet.getString("NAME"), resultSet.getInt("RATE"), resultSet.getDouble("PRICE"), resultSet.getInt("CATEGORY_ID")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return products;
    }

}
