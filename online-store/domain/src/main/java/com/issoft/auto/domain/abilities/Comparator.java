package com.issoft.auto.domain.abilities;

import com.issoft.auto.domain.Product;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Comparator {

    HandleReader handleReader = new HandleReader();

    private List<Product> sortByName(List<Product> products) throws ParserConfigurationException, SAXException, IOException {

        List<String> names = new ArrayList<>();
        for (Product product : products) {
            names.add(product.getName());
        }
        Collections.sort(names);

        List<Product> sortedProductsByName = new ArrayList<>();
        for (String name : names) {
            for (int j = 0; j < products.size(); j++) {
                if (name == products.get(j).getName()) {
                    sortedProductsByName.add(products.get(j));
                    products.remove(j);
                    break;
                } else continue;
            }
        }

        if (!handleReader.isSortAsc("name")) Collections.reverse(sortedProductsByName);
        return sortedProductsByName;
    }

    private List<Product> sortByPrice(List<Product> products) throws ParserConfigurationException, SAXException, IOException {

        List<Double> prices = new ArrayList<>();
        for (Product product : products) {
            prices.add(product.getPrice());
        }
        Collections.sort(prices);

        List<Product> sortedProductsByPrice = new ArrayList<>();
        for (Double price : prices) {
            for (int j = 0; j < products.size(); j++) {
                if (price == products.get(j).getPrice()) {
                    sortedProductsByPrice.add(products.get(j));
                    products.remove(j);
                    break;
                } else continue;
            }
        }

        if (!handleReader.isSortAsc("price")) Collections.reverse(sortedProductsByPrice);
        return sortedProductsByPrice;
    }

    private List<Product> sortByRate(List<Product> products) throws ParserConfigurationException, SAXException, IOException {

        List<Integer> rates = new ArrayList<>();
        for (Product product : products) {
            rates.add(product.getRate());
        }
        Collections.sort(rates);

        List<Product> sortedProductsByRate = new ArrayList<>();
        for (Integer rate : rates) {
            for (int j = 0; j < products.size(); j++) {
                if (rate == products.get(j).getRate()) {
                    sortedProductsByRate.add(products.get(j));
                    products.remove(j);
                    break;
                } else continue;
            }
        }
        if (!handleReader.isSortAsc("price")) Collections.reverse(sortedProductsByRate);
        return sortedProductsByRate;
    }

    public List<Product> sortProductsByNamePriceRate(List<Product> products) throws IOException, SAXException, ParserConfigurationException {

        List<Product> sortedProductsByName = sortByName(products);
        List<Product> sortedProductsByNamePrice = sortedProductsByName;
        for (int i = 0; i < sortedProductsByName.size(); i++){

            List<Product> sameValues = new ArrayList<>();
            sameValues.add(sortedProductsByName.get(i));

            if (i == (sortedProductsByName.size()-1)) {
                break;
            } else {
                for (int j = i; j < sortedProductsByName.size(); j++){

                    if (sortedProductsByName.get(j).getName() == sortedProductsByName.get(j++).getName()){
                        sameValues.add(sortedProductsByName.get(i++));
                    } else j = sortedProductsByName.size();

                }

                if (sameValues.size() > 1) {
                    sortByPrice(sameValues);
                    for (int k = 0; k < sameValues.size(); k++){
                        sortedProductsByNamePrice.get(i).equals(sameValues.get(k));
                        i++;
                    }
                } else break;
            }
        }

        List<Product> sortedProductsByNamePriceRate = sortedProductsByNamePrice;
        for (int i = 0; i < sortedProductsByNamePrice.size(); i++){

            List<Product> sameValues = new ArrayList<>();
            sameValues.add(sortedProductsByNamePrice.get(i));

            if (i == (sortedProductsByNamePrice.size()-1)) {
                break;
            } else {
                for (int j = i; j < sortedProductsByNamePrice.size(); j++){

                    if ((sortedProductsByNamePrice.get(j).getName() == sortedProductsByNamePrice.get(j++).getName()) && (sortedProductsByNamePrice.get(j--).getPrice() == sortedProductsByNamePrice.get(j+=2).getPrice())){
                        sameValues.add(sortedProductsByNamePrice.get(i++));
                    } else j = sortedProductsByNamePrice.size();

                }

                if (sameValues.size() > 1) {
                    sortByRate(sameValues);
                    for (int k = 0; k < sameValues.size(); k++){
                        sortedProductsByNamePriceRate.get(i).equals(sameValues.get(k));
                        i++;
                    }
                } else break;
            }
        }

        return sortedProductsByNamePriceRate;

    }

}
