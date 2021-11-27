package com.issoft.auto.consoleApp;

import com.issoft.auto.store.Store;

public class StoreApp {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Store store = new Store();
        store.printStoreData();
    }

}
