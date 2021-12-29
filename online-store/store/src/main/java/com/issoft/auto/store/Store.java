package com.issoft.auto.store;

import java.util.ArrayList;
import java.util.List;

import com.issoft.auto.domain.Category;
import com.issoft.auto.domain.Product;
import com.issoft.auto.store.utils.Helper;

public class Store {

    RandomStorePopulator randomStorePopulator = new RandomStorePopulator();

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public Store() throws InstantiationException, IllegalAccessException {
        this.categories = randomStorePopulator.getCategoriesForShop();
    }

    public void printStoreData() {
        for (Category category: categories){
            System.out.println("\n" + "--------------------------------------------");
            System.out.println("\n" + "CATEGORY NAME: " + category.getName());
            System.out.println("\n" + "PRODUCTS: ");
            Helper.printProducts(category.getProducts());
        }
    }


    public List<Product> getAllProducts() {
        List<Product> allProductsFromAllCategories = new ArrayList<>();
        for (Category category: categories){
            for(Product product: category.getProducts()){
                allProductsFromAllCategories.add(product);
            }
        }
        return allProductsFromAllCategories;
    }

}
