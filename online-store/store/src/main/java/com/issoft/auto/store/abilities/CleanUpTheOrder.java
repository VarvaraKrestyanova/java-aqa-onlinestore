package com.issoft.auto.store.abilities;

import com.issoft.auto.domain.Product;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CleanUpTheOrder extends Thread {

    List<Product> productsOrder;
    Lock lock = new ReentrantLock();

    public void cleanOrderList(List<Product> productsOrder) {
        this.productsOrder = productsOrder;
    }

    public void run() {

        while (Thread.currentThread().isAlive()){
            lock.lock();
            if (GenerateOrder.isOrderActive == false){
                lock.unlock();
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



}
