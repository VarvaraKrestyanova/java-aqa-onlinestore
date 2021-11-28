package com.issoft.auto.domain;

import java.util.List;

public class Category {

    private String name;
    private List<Product> products;
    private int numberOfProducts;
    private String productName;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public String getProductName() {
        return productName;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
