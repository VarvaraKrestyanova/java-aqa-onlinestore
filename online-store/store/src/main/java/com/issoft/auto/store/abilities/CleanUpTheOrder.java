package com.issoft.auto.store.abilities;

import com.issoft.auto.domain.Product;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CleanUpTheOrder extends Thread {

    List<Product> productsOrder;

    public void cleanOrderList(List<Product> productsOrder) {
        this.productsOrder = productsOrder;
    }

    public void run() {
        while (Thread.currentThread().isAlive()){
            try {
                TimeUnit.MINUTES.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            productsOrder.clear();
            System.out.println("You have placed no orders.");
        }
    }

}
