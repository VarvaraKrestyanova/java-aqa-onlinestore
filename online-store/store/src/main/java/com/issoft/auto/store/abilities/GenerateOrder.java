package com.issoft.auto.store.abilities;

import com.github.javafaker.Faker;
import com.issoft.auto.domain.Product;
import com.issoft.auto.store.utils.Helper;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GenerateOrder extends Thread{

    List<Product> productList;
    Product chosenProduct;
    List<Product> purchasedGoods;

    public void createOrderForProducts (Product chosenProduct, List<Product> productList, List<Product> purchasedGoods) {
        this.chosenProduct = chosenProduct;
        this.productList = productList;
        this.purchasedGoods = purchasedGoods;
    }

    public void run() {
        System.out.println("Your order is in progress...");
        try {
            TimeUnit.SECONDS.sleep(timeOfOrderProcess());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        purchasedGoods.add(chosenProduct);
        System.out.println("Your order is created. Purchased Product: ");
        Helper.printProducts(purchasedGoods);
    }

    public int timeOfOrderProcess() {
        Faker faker = new Faker();
        int timeNumber = faker.number().numberBetween(1,30);
        return timeNumber;
    }

}
