package com.issoft.auto.store.abilities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadsStarter {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void startThread(){
        executorService.execute(() -> {
            System.out.println("Thread is in progress: " + Thread.currentThread().getName());
        });
    }

    public void finishThread(){
        System.out.println("Thread is finished: " + Thread.currentThread().getName());
        executorService.shutdown();
    }

}
