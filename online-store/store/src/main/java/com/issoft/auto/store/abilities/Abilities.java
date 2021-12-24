package com.issoft.auto.store.abilities;

import com.issoft.auto.domain.Product;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.issoft.auto.store.utils.Helper.printProducts;

public class Abilities {

    Comparator comparator = new Comparator();
    Map<String, String> configSort;

    public Abilities(String filePackageName, String fileName) {
        try {
            configSort = new Reader().readXmlConfigSort(filePackageName, fileName);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void sortAndPrint(List<Product> products) {
        List<Product> resultList = comparator.sortBy(products, configSort);
        printProducts(resultList);
    }



    public void printTop5ByPriceDesc(List<Product> products) throws SAXException, ParserConfigurationException, IOException {
        Map<String, String> sortMap = new HashMap<>();
        sortMap.put("price", "desc");

        List<Product> sortedByPrice = comparator.sortBy(products, sortMap);

        if(sortedByPrice.size() >= 5){
            printProducts(sortedByPrice.subList(0, 5));
        } else System.out.println("There are less than 6 products");

    }

    public void quit(){
        System.exit(0);
    }

}
