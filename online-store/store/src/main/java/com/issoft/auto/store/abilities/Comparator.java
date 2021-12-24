package com.issoft.auto.store.abilities;

import com.issoft.auto.domain.Product;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Comparator {

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

    private Object getProductValueByAttribute(Product product, String attributeName){
        return getValue(product, nameOfGetMethod(attributeName));
    }

    public List<Product> sortBy(List<Product> products, Map<String, String> sortXmlConfig) {

        java.util.Comparator comparator = new java.util.Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                CompareToBuilder compareToBuilder = new CompareToBuilder();
                sortXmlConfig.entrySet().forEach(e->{
                    if("asc".equalsIgnoreCase(e.getValue())) {
                        compareToBuilder.append(getProductValueByAttribute(o1, e.getKey()), getProductValueByAttribute(o2, e.getKey()));
                    } else {
                        compareToBuilder.append(getProductValueByAttribute(o2, e.getKey()), getProductValueByAttribute(o1, e.getKey()));
                    }
                });

                return compareToBuilder.toComparison();
            }
        };
        Collections.sort(products, comparator);
        return products;
    }

}
