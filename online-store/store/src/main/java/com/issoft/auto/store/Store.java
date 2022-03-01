package com.issoft.auto.store;

import java.io.IOException;
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
    private static Store store;

    public List<Category> getCategories() {
        return categories;
    }

    public Store() throws InstantiationException, IllegalAccessException, IOException {
        System.out.println("Please, enter '1' if you wanna see the store from Data Base and '2' if from Server!");
        System.out.println("Please, enter any other symbol if you wanna see the Random default store!");
        Scanner scanner = new Scanner(System.in);
        String chosenStore = scanner.nextLine();

        if (chosenStore.equals("1")){
            RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
            this.categories = randomStorePopulator.getCategoriesForShop();
        } else if (chosenStore.equals("2")){
            RandomHttpPopulator randomHttpPopulator = new RandomHttpPopulator();
            this.categories = randomHttpPopulator.getCategoriesForShop();
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

    public static Store getInstance() throws IllegalAccessException, InstantiationException, IOException {
        if (store==null) {
            store = new Store();
        }
        return store;
    }

}
