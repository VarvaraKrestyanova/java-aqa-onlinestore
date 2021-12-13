package com.issoft.auto.store;

import java.util.ArrayList;
import java.util.List;

import com.issoft.auto.domain.Category;
import com.issoft.auto.domain.Product;

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

    private List<Product> allProductsFromAllCategories = new ArrayList<>();

    public void printStoreData() {
        for (Category category: categories){
            System.out.println("\n" + "--------------------------------------------");
            System.out.println("\n" + "CATEGORY NAME: " + category.getName());
            System.out.println("\n" + "PRODUCTS: ");
            for(Product product: category.getProducts()){
                System.out.println("Name: " + product.getName() + ", Rate: " + product.getRate() + ", Price: " + product.getPrice());
                allProductsFromAllCategories.add(product);
            }
        }
    }


    public List<Product> getAllProducts() {
        return allProductsFromAllCategories;
    }

}
