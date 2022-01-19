package com.issoft.auto.store;

import com.github.javafaker.Faker;
import com.issoft.auto.domain.Category;
import com.issoft.auto.domain.Product;
import com.issoft.auto.domain.db.DBManager;

import java.util.ArrayList;
import java.util.List;

public class RandomStorePopulator {

    public List<Category> getCategoriesForShop() {

        List<Category> categoriesDB = DBManager.getAllCategories();
        List<Product> productsDB = DBManager.getAllProducts();

        for (Category categoryDB : categoriesDB) {
            List<Product> products = new ArrayList();
            for (Product productDB : productsDB) {
                if (productDB.getCategory_id() == categoryDB.getId()) {
                    products.add(productDB);
                }
            }
            categoryDB.setProducts(products);
        }
        return categoriesDB;
    }
}
