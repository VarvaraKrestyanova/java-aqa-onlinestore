package com.issoft.auto.consoleApp;

import com.issoft.auto.domain.Product;
import com.issoft.auto.domain.abilities.Abilities;
import com.issoft.auto.domain.abilities.Reader;
import com.issoft.auto.store.Store;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoreApp {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException, SAXException, ParserConfigurationException, NoSuchMethodException, InvocationTargetException {
        Store store = new Store();
        Abilities abilities = new Abilities();

        System.out.print("---------------------" + "\n" + "WELCOME TO THE STORE!" + "\n" + "---------------------");
        store.printStoreData();

        Scanner in = new Scanner(System.in);
        int chosenAbility = 0;

        while (chosenAbility != 4){
            System.out.println("\n" + "Choose option:");
            System.out.println("\n" + "'print' - Input 1 to print all Categories and Products");
            System.out.println("\n" + "'sort' - Input 2 to print sorted Products by all attributes");
            System.out.println("\n" + "'top' - Input 3 to print top 5 Products");
            System.out.println("\n" + "'quit' - Input 4 to exit");
            chosenAbility = in.nextInt();
            switch (chosenAbility){
                case 1: store.printStoreData(); break;
                case 2: abilities.sortAndPrint(store.getAllProducts());break;
                case 3: abilities.printTop5ByPriceDesc(store.getAllProducts());break;
                case 4: abilities.quit();
            }
        }


    }

}
