package com.issoft.auto.domain.abilities;

import com.issoft.auto.domain.Product;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Comparator {

    HandleReader handleReader = new HandleReader();

    private Object getValue(Product product, String valueName){
        Object resultValue = null;
        try {
            resultValue = product.getClass().getMethod(valueName).invoke(product);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return resultValue;
    }

    private String nameOfGetMethod(String attributeName){
        return "get" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
    }

    public void reverseList(List<?> listName){
        Collections.reverse(listName);
    }

    public List<Product> sortBy(List<Product> products, String attributeNameToSortBy) {

        Collections.sort(products, new java.util.Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return String.valueOf(getValue(o1, nameOfGetMethod(attributeNameToSortBy))).compareTo(String.valueOf(getValue(o2, nameOfGetMethod(attributeNameToSortBy))));
            }
        });

        if (!handleReader.isSortAsc(attributeNameToSortBy)) reverseList(products);
        return products;

    }

    public List<Product> sortByAllAttributes (List<Product> products) {

        List<String> attributeNames = handleReader.attributeNames();

        Collections.sort(products, new java.util.Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                CompareToBuilder compareToBuilder = new CompareToBuilder();

                for (String attributeName : attributeNames) {
                    if (handleReader.isSortAsc(attributeName)){
                        compareToBuilder = compareToBuilder.append(getValue(product1, nameOfGetMethod(attributeName)), getValue(product2, nameOfGetMethod(attributeName)));
                    } else {
                        compareToBuilder = compareToBuilder.append(getValue(product2, nameOfGetMethod(attributeName)), getValue(product1, nameOfGetMethod(attributeName)));
                    }


                }

                return compareToBuilder.toComparison();
            }
        });

        return products;
    }

}
