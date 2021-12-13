package com.issoft.auto.domain.abilities;

import com.issoft.auto.domain.Product;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Comparator {

    HandleReader handleReader = new HandleReader();

    private Object getValue(Product product, String valueName) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return product.getClass().getMethod(valueName).invoke(product);
    }

    private String nameOfGetMethod(String attributeName){
        return "get" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
    }

    public void reverseList(List<?> listName){
        Collections.reverse(listName);
    }

    public List<Product> sortBy(List<Product> products, String attributeNameToSortBy) throws ParserConfigurationException, SAXException, IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        List<String> valuesBasedOnAttribute = new ArrayList<>();
        for (Product product : products) {
            valuesBasedOnAttribute.add(getValue(product, nameOfGetMethod(attributeNameToSortBy)).toString());
        }
        Collections.sort(valuesBasedOnAttribute);

        List<Product> sortedProductsByAttribute = new ArrayList<>();
        for (String value : valuesBasedOnAttribute) {
            for (int j = 0; j < products.size(); j++) {
                if (value.equals(getValue(products.get(j), nameOfGetMethod(attributeNameToSortBy)).toString())) {
                    sortedProductsByAttribute.add(products.get(j));
                    products.remove(j);
                    break;
                } else continue;
            }
        }
        if (!handleReader.isSortAsc(attributeNameToSortBy)) reverseList(sortedProductsByAttribute);
        return sortedProductsByAttribute;

    }

    public List<Product> sortByAllAttributes(List<Product> products) throws NoSuchMethodException, IllegalAccessException, SAXException, ParserConfigurationException, InvocationTargetException, IOException {

        List<String> attributeNames = handleReader.attributeNames();
        List<Product> sortedByFirstAtr = sortBy(products, attributeNames.get(0));
        List<Product> sortedProductsBy = sortedByFirstAtr;

        for (int m = 1; m < attributeNames.size(); m++){
            for (int i = 0; i < sortedByFirstAtr.size(); i++){

                List<Product> sameValues = new ArrayList<>();
                sameValues.add(sortedByFirstAtr.get(i));

                if (i == (sortedByFirstAtr.size()-1)) break;
                else {

                    for (int j = i; j < sortedByFirstAtr.size(); j++){

                        if ((getValue(sortedByFirstAtr.get(j), nameOfGetMethod(attributeNames.get(m-1))).toString()) == (getValue(sortedByFirstAtr.get(j+1), nameOfGetMethod(attributeNames.get(m-1))).toString())) {
                            sameValues.add(sortedByFirstAtr.get(i++));
                        } else {
                            j = sortedByFirstAtr.size();

                        }
                    }

                    if (sameValues.size() > 1) {
                        sortBy(sameValues, attributeNames.get(m));
                        int y = i - sameValues.size() + 1;
                        for (int k = 0; k < sameValues.size(); k++){
                            sortedProductsBy.set(y, sameValues.get(k));
                            y++;
                        }
                    } else break;
                }
            }
        }

        return sortedProductsBy;
    }

}
