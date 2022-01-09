package com.issoft.auto.store.chain;

import com.issoft.auto.domain.Product;
import com.issoft.auto.store.Store;
import com.issoft.auto.store.abilities.Abilities;
import com.issoft.auto.store.abilities.CleanUpTheOrder;
import com.issoft.auto.store.abilities.GenerateOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderAction extends Action{

    public OrderAction(int actionNumber) {
        super(actionNumber);
    }

    @Override
    public void writeData(Store store, Abilities abilities) {
        executeOrder(store.getAllProducts());
    }

    @Override
    public String getCommandName(){
        return "order";
    }

    public void executeOrder(List<Product> productList) {

        GenerateOrder generateOrder = new GenerateOrder();
        List<Product> purchasedGoods = new ArrayList<>();
        String name = ""; int rate = 0; double price = 0;

        System.out.println("Please, enter product name for order: ");
        Scanner scanner = new Scanner(System.in);
        String productName = scanner.nextLine();
        for (Product product: productList){
            if (product.getName().equals(productName)){
                name = product.getName();
                rate = product.getRate();
                price = product.getPrice();
            }
        }

        Product chosenProduct = new Product(name, rate, price);

        generateOrder.createOrderForProducts(chosenProduct, productList, purchasedGoods);
        generateOrder.start();

        CleanUpTheOrder cleanUpTheOrder = new CleanUpTheOrder();
        cleanUpTheOrder.cleanOrderList(purchasedGoods);
        cleanUpTheOrder.start();
    }

}
