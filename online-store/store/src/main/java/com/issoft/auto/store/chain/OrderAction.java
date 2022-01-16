package com.issoft.auto.store.chain;

import com.issoft.auto.domain.Product;
import com.issoft.auto.store.Store;
import com.issoft.auto.store.abilities.Abilities;
import com.issoft.auto.store.abilities.GenerateOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderAction extends Action{

    public OrderAction(int actionNumber) {
        super(actionNumber);
    }

    public static List<Product> purchasedGoods = new ArrayList<>();

    @Override
    public void writeData(Store store, Abilities abilities) {

        GenerateOrder generateOrder = new GenerateOrder();

        Product chosenProduct = null;
        List<Product> productList = store.getAllProducts();

        System.out.println("Please, enter product name for order: ");
        Scanner scanner = new Scanner(System.in);
        String productName = scanner.nextLine();
        for (Product product: productList){
            if (product.getName().equals(productName)){
                chosenProduct = product;
            }
        }

        generateOrder.createOrderForProducts(chosenProduct, purchasedGoods);
        generateOrder.start();

    }

    @Override
    public String getCommandName(){
        return "order";
    }

}
