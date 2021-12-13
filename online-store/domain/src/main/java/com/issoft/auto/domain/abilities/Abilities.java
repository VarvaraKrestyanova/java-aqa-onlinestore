package com.issoft.auto.domain.abilities;

import com.issoft.auto.domain.Product;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Abilities {

    Comparator comparator = new Comparator();

    public void sortAndPrint(List<Product> products) throws ParserConfigurationException, SAXException, IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        for (Product product: comparator.sortByAllAttributes(products)){
            System.out.println("Name: " + product.getName() + ", Rate: " + product.getRate() + ", Price: " + product.getPrice());
        }
    }

    public void printTop5ByPriceDesc(List<Product> products) throws NoSuchMethodException, IllegalAccessException, SAXException, ParserConfigurationException, InvocationTargetException, IOException {
        HandleReader handleReader = new HandleReader();
        List<Product> sortedByPrice = comparator.sortBy(products, "price");
        if (handleReader.isSortAsc("price")) comparator.reverseList(sortedByPrice);

        if(sortedByPrice.size() >= 5){
            for (Product product: sortedByPrice.subList(0, 5)){
                System.out.println("Name: " + product.getName() + ", Rate: " + product.getRate() + ", Price: " + product.getPrice());
            }
        } else System.out.println("There are less than 6 products");

    }

    public void quit(){
        System.exit(0);
    }

}
