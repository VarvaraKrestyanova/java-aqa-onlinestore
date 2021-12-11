package com.issoft.auto.consoleApp;

import com.issoft.auto.domain.abilities.Abilities;
import com.issoft.auto.domain.abilities.Comparator;
import com.issoft.auto.domain.abilities.Reader;
import com.issoft.auto.store.Store;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class StoreApp {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException, SAXException, ParserConfigurationException {
        Store store = new Store();
        store.printStoreData();
        Reader reader = new Reader();
        //--------
        System.out.println("\n" + reader.readXmlConfigSort());

        Abilities abilities = new Abilities();


        System.out.print("---------------------" + "\n" + "WELCOME TO THE STORE!" + "\n" + "---------------------");
        Scanner in = new Scanner(System.in);
        int chosenAbility = 0;

        while (chosenAbility != 4){
            System.out.println("\n" + "Choose option:");
            System.out.println("\n" + "Input 1 to print all Categories and Products");
            System.out.println("\n" + "Input 2 to print sorted Products by attributes");
            System.out.println("\n" + "Input 3 to print top 5 Products");
            System.out.println("\n" + "Input 4 to exit");
            chosenAbility = in.nextInt();
            switch (chosenAbility){
                case 1: store.printStoreData(); break;
                case 2: abilities.sortAndPrint(store.getAllProducts());break;   //дублируются. надо чекать не только имя, но и имя+цена
                case 3:
                    System.out.println(" print top 5 products sorted via price desc");break;
                case 4: abilities.quit();
            }
        }


    }

}
