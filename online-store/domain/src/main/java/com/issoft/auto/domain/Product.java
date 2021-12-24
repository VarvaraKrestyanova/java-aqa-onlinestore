package com.issoft.auto.domain;

public class Product{

    private String name;
    private int rate;
    private double price;

    public Product(String name, int rate, double price){
        this.name = name;
        this.rate = rate;
        this.price = price;
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

    @Override
    public String toString() {
        return "Product Name: " + name + ", Rate: " + rate + ", Price: " + price;
    }
}
