package com.issoft.auto.store.utils;

import com.issoft.auto.domain.Product;

import java.util.List;

public class Helper {
    public static void printProducts(List<Product> products){
        products.forEach(System.out::println);
    }
}
