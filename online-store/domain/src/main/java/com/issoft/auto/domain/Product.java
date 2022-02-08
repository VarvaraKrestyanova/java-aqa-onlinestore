package com.issoft.auto.domain;

public class Product{

    private String name;
    private int rate;
    private double price;
    private String categoryName;

    public Product(String name, int rate, double price, String categoryName){
        this.name = name;
        this.rate = rate;
        this.price = price;
        this.categoryName = categoryName;
    }

    public String getName(){
        return name;
    }

    public int getRate(){
        return rate;
    }

    public double getPrice(){
        return price;
    }

    public String getCategoryName(){
        return categoryName;
    }

    @Override
    public String toString() {
        return "Product Name: " + name + ", Rate: " + rate + ", Price: " + price;
    }
}
