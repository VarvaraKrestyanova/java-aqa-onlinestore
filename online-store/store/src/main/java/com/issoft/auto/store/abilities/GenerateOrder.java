package com.issoft.auto.store.abilities;

import com.github.javafaker.Faker;
import com.issoft.auto.domain.Product;
import com.issoft.auto.store.utils.Helper;

import java.util.List;
import java.util.concurrent.*;

public class GenerateOrder extends Thread{

    Product chosenProduct;
    List<Product> purchasedGoods;
    public static ThreadsStarter threadsStarter = new ThreadsStarter();
    public static boolean isOrderActive = false;

    public void createOrderForProducts (Product chosenProduct, List<Product> purchasedGoods) {
        this.chosenProduct = chosenProduct;
        this.purchasedGoods = purchasedGoods;
    }

    public void run() {
        isOrderActive = true;
        threadsStarter.startThread();

        System.out.println("Your order is in progress...");
        try {
            TimeUnit.SECONDS.sleep(timeOfOrderProcess());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        purchasedGoods.add(chosenProduct);
        System.out.println("Your order is created. Purchased Product: ");
        Helper.printProducts(purchasedGoods);
        isOrderActive = false;
    }

    public int timeOfOrderProcess() {
        Faker faker = new Faker();
        int timeNumber = faker.number().numberBetween(1,30);
        return timeNumber;
    }

}
