package com.issoft.auto.domain.db;

import com.issoft.auto.domain.Category;
import com.issoft.auto.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {


    public static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionUtil.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORY;");
            while (resultSet.next()){
                categories.add(new Category(resultSet.getString("NAME")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return categories;
    }

    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionUtil.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT;");
            while (resultSet.next()) {
                products.add(new Product
                        (resultSet.getString("NAME"), resultSet.getInt("RATE"), resultSet.getDouble("PRICE"), resultSet.getString("CATEGORY_NAME")));
            }
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return products;
        }
    }

    /*************************************
    ***IF WE HAVEN'T AVAILABLE DB TABLE***
    *************************************/

    public static void createCategoryTableForDB(){
        try {
            Connection connection = ConnectionUtil.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS \"CATEGORY\"" +
                    "(" +
                    "  \"NAME\" VARCHAR(255) NOT NULL" +
                    ");");
            statement.close();
            connection.commit();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void createProductTableForDB(){
        try {

            Connection connection = ConnectionUtil.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS \"PRODUCT\"" +
                    " (" +
                    "\"NAME\" VARCHAR(255) NOT NULL," +
                    "\"RATE\" INT NOT NULL," +
                    "\"PRICE\" DOUBLE NOT NULL," +
                    "\"CATEGORY_NAME\" VARCHAR(255) NOT NULL" +
                    ");");
            statement.close();
            connection.commit();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addRandomCategoryToCategoryTable(String categoryName) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(String.format("INSERT INTO CATEGORY VALUES ('%s')", categoryName));
        statement.close();
        connection.close();
    }

    public static void addRandomProductToProductTable(Product product) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(String.format("INSERT INTO PRODUCT VALUES ('%s',%d, %f,'%s')", product.getName(), product.getRate(), product.getPrice(), product.getCategoryName()));
        statement.close();
        connection.close();
    }

    public static boolean doesTableHaveData() throws SQLException {
        boolean isDataExist = false;
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM PRODUCT;");
        resultSet.next();
        if (resultSet.getInt(1) > 0){
            isDataExist = true;
        }
        return isDataExist;
    }

}
