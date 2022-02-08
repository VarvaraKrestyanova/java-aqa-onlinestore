package com.issoft.auto.domain.db;

import com.issoft.auto.domain.Category;
import com.issoft.auto.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static final String url = "jdbc:h2:~/OnlineStoreData";
    private static final String user = "***";
    private static final String password = "***";

    public static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        try {
            //Connection connection = ConnectionUtil.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORY;");
            while (resultSet.next()){
                categories.add(new Category(resultSet.getString("NAME")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return categories;
    }

    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try {
            //connection = ConnectionUtil.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT;");
            while (resultSet.next()){
                products.add(new Product
                        (resultSet.getString("NAME"), resultSet.getInt("RATE"), resultSet.getDouble("PRICE"), resultSet.getString("CATEGORY_NAME")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return products;
    }

    /*************************************
    ***IF WE HAVEN'T AVAILABLE DB TABLE***
    *************************************/

    public static boolean isDBTableAvailable(String tableNamePatterm) throws SQLException {
        DatabaseMetaData databaseMetaData  = connection.getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(null, null, tableNamePatterm, new String[]{"TABLE"});
        return resultSet.next();
    }

    public static void createCategoryTableForDB(){
        try {

            Statement statement = connection.createStatement();
            statement.executeQuery("CREATE TABLE \"CATEGORY\"\n" +
                    "(\n" +
                    "  \"NAME\" VARCHAR(255) NOT NULL,\n" +
                    "  CONSTRAINT \"CATEGORY_PKEY\" PRIMARY KEY (\"NAME\")\n" +
                    ");");

            statement.close();
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void createProductTableForDB(){
        try {

            Statement statement = connection.createStatement();
            statement.executeQuery("CREATE TABLE \"PRODUCT\"\n" +
                    " (\n" +
                    "\"NAME\" VARCHAR(255) NOT NULL,\n" +
                    "\"RATE\" INT NOT NULL,\n" +
                    "\"PRICE\" DOUBLE NOT NULL,\n" +
                    "\"CATEGORY_NAME\" VARCHAR(255) NOT NULL,\n" +
                    "CONSTRAINT \"PRODUCT_FKEY\" FOREIGN KEY (\"NAME\") REFERENCES \"CATEGORY\" (\"NAME\")\n" +
                    ");");

            statement.close();
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addRandomCategoryToCategoryTable(String categoryName) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(String.format("INSERT INTO CATEGORY VALUES ('%s')", categoryName));
    }

    public static void addRandomProductToProductTable(Product product) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(String.format("INSERT INTO PRODUCT VALUES ('%s',%d,%f,'%s')", product.getName(), product.getRate(), product.getPrice(), product.getCategoryName()));
    }

    /**************************
     * Connection *************
     *************************/

    public static Connection connection = null;

    public DBManager(){
        if (connection == null){
            connection = ConnectionUtil.getConnection(url, user, password);
        }
    }

    public static void closeConnectionAndAddNull(){
        try {
            if (connection != null){
                connection.close();
            }
        } catch (SQLException throwables) {
            System.out.println("Exception during DB closing: " + throwables.getMessage());
        }
        connection = null;
    }



}
