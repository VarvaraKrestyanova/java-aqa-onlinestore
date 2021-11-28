package com.issoft.auto.store;

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

    public void printStoreData() {
        for (Category category: categories){
            System.out.println("\n" + "--------------------------------------------");
            System.out.println("\n" + "CATEGORY NAME: " + category.getName());
            System.out.println("\n" + "PRODUCTS: ");
            for(Product product: category.getProducts()){
                System.out.println("Name: " + product.getName() + ", Rate: " + product.getPrice() + ", Price: " + product.getPrice());
            }
        }
    }

}
