package com.issoft.auto.domain.abilities;

import com.issoft.auto.domain.Product;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Abilities {

    Comparator comparator = new Comparator();

    public void sortAndPrint(List<Product> products) throws ParserConfigurationException, SAXException, IOException {
        for (Product product: comparator.sortProductsByNamePriceRate(products)){
            System.out.println("Name: " + product.getName() + ", Rate: " + product.getPrice() + ", Price: " + product.getPrice());
        }
    }

    public void printTop5(List<Product> products){}

    public void quit(){
        System.exit(0);
    }

}
