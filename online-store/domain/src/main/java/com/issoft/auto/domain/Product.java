package com.issoft.auto.domain;

public class Product{

    private String name;
    private int rate;
    private double price;
    private int category_id;

    public Product(String name, int rate, double price, int category_id){
        this.name = name;
        this.rate = rate;
        this.price = price;
        this.category_id = category_id;
    }

    public int getCategory_id() {
        return category_id;
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
