package com.issoft.auto.domain.server;

import com.issoft.auto.domain.Category;
import com.issoft.auto.domain.Product;
import com.issoft.auto.domain.db.DBManager;
import com.issoft.auto.store.RandomDataBasePopulator;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoreServer {

    public static final String CATEGORIES_URL = String.format("http://localhost:8088/categories");
    public static final String PRODUCTS_URL = String.format("http://localhost:8088/products");
    public static final String CART_URL = String.format("http://localhost:8088/cart");

    public StoreServer() {
        run();
    }

    private void run() {

        try {

            HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 8088), 0);

            List<Category> categories = DBManager.getAllCategories();
            for (Category category : categories) {
                server.createContext(String.format("products/%s", category.getName()), new ProductsHandler(category.getName(), this))
                        .setAuthenticator(new ServerAuthenticator("realm"));
            }
            server.createContext("/categories", new CategoriesHandler(this))
                    .setAuthenticator(new ServerAuthenticator("realm"));
            server.createContext("/cart", new Cart(this))
                    .setAuthenticator(new ServerAuthenticator("realm"));
            server.setExecutor(null);
            server.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    RandomDataBasePopulator randomDataBasePopulator = new RandomDataBasePopulator();

    public List<Category> getCategories() {
        return randomDataBasePopulator.getCategoriesForShop();
    }

    public List<Product> getProductsOfCategory(String categoryName) {
        List<Product> products = null;
        for (Category category : getCategories()) {
            if (category.getName().equals(categoryName)) {
                products.addAll(category.getProducts());
            }
        }
        return products;
    }

    public List<Product> allProducts() {
        List<Product> products = null;
        for (Category category : getCategories()) {
            products.addAll(category.getProducts());
        }
        return products;
    }

    public static List<Product> addProductsOnCart = new ArrayList<>();

    public Product addProductToCart (String productName) {
        Product product = getSelectedProduct(productName);
        addProductsOnCart.add(product);
        return product;
    }

    private Product getSelectedProduct(String productName)
    {
        Optional<Product> orderedProduct =  allProducts().stream()
                .filter(x -> x.getName().equals(productName))
                .findFirst();

        Product product = orderedProduct.isPresent() ? orderedProduct.get() : null;

        return product;
    }

    public List<Product> getProductsOnCart() {
        return addProductsOnCart;
    }


}
