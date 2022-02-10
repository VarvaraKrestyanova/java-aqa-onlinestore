package com.issoft.auto.store;

import com.issoft.auto.domain.Category;
import com.issoft.auto.domain.Product;
import com.issoft.auto.domain.db.DBManager;
import com.issoft.auto.store.utils.IPopulator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RandomDataBasePopulator implements IPopulator {

    @Override
    public List<Category> getCategoriesForShop() {

        try {
            createDBTableWithRandomData();
        } catch (SQLException | InstantiationException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }

        List<Category> categoriesDB = DBManager.getAllCategories();
        List<Product> productsDB = DBManager.getAllProducts();

        for (Category categoryDB : categoriesDB) {
            List<Product> products = new ArrayList();
            for (Product productDB : productsDB) {
                if (productDB.getCategoryName() == categoryDB.getName()) {
                    products.add(productDB);
                }
            }
            categoryDB.setProducts(products);
        }
        return categoriesDB;
    }


    public void createDBTableWithRandomData() throws InstantiationException, IllegalAccessException, SQLException {

        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
        List<Category> categories = randomStorePopulator.getCategoriesForShop();

        DBManager.createCategoryTableForDB();
        DBManager.createProductTableForDB();

        if (DBManager.doesTableHaveData() == false) {
            for (Category category : categories){
                DBManager.addRandomCategoryToCategoryTable(category.getName());
                List<Product> productsOfCategory = category.getProducts();
                for (Product product : productsOfCategory){
                    DBManager.addRandomProductToProductTable(product);
                }
            }
        }
    }

}
