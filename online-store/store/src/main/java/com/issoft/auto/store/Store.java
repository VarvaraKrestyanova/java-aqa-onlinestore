package com.issoft.auto.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.issoft.auto.domain.Category;
import com.issoft.auto.domain.Product;
import com.issoft.auto.store.utils.Helper;

public class Store {

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public Store() throws InstantiationException, IllegalAccessException {
        System.out.println("Please, enter '+' if you wanna see the store from Data Base!");
        System.out.println("Please, enter any other symbol if you wanna see the Random default store!");
        Scanner scanner = new Scanner(System.in);
        String chosenStore = scanner.nextLine();

        if (chosenStore.equals("-")){
            RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
            this.categories = randomStorePopulator.getCategoriesForShop();
        } else {
            RandomDataBasePopulator randomDataBasePopulator = new RandomDataBasePopulator();
            this.categories = randomDataBasePopulator.getCategoriesForShop();
        }
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
