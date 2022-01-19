package com.issoft.auto.domain;

import java.util.List;

public class Category {

    private int id;
    private String name;
    private List<Product> products;

    public Category(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
