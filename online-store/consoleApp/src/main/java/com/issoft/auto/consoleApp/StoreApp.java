package com.issoft.auto.consoleApp;

import com.issoft.auto.store.abilities.Abilities;
import com.issoft.auto.store.Store;
import com.issoft.auto.store.chain.*;

import java.util.*;

public class StoreApp {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        Action printAction = new PrintAction(AvailableAction.PRINT.getActionNumber());
        Action sortAction = new SortAction(AvailableAction.SORT.getActionNumber());
        Action topAction = new TopAction(AvailableAction.TOP.getActionNumber());
        Action quitAction = new QuitAction(AvailableAction.QUIT.getActionNumber());

        printAction.setNextAction(sortAction);
        sortAction.setNextAction(topAction);
        topAction.setNextAction(quitAction);

        System.out.println("Please, enter PACKAGE name containing xml file with store parameters (enter '1' if it's default value - domain): ");
        Scanner scanner = new Scanner(System.in);
        String filePackageName = scanner.nextLine();
        if (filePackageName.equals("1")){
            filePackageName = "domain";
        }
        System.out.println("Please, enter xml FILE name containing store parameters (enter '1' if it's default value - configSort): ");
        String fileName = scanner.nextLine();
        if (fileName.equals("1")){
            fileName = "configSort";
        }

        Store store = new Store();
        Abilities abilities = new Abilities(filePackageName, fileName);

        System.out.print("---------------------" + "\n" + "WELCOME TO THE STORE!" + "\n" + "---------------------");

        Scanner in = new Scanner(System.in);
        int chosenAbility = 0;

        while (chosenAbility <= 4){
            System.out.println("\n" + "Choose option:");
            System.out.println("\n" + "'print' - Input 1 to print all Categories and Products");
            System.out.println("\n" + "'sort' - Input 2 to print sorted Products by all attributes");
            System.out.println("\n" + "'top' - Input 3 to print top 5 Products");
            System.out.println("\n" + "'quit' - Input 4 to exit");

            chosenAbility = in.nextInt();
            printAction.callTheAction(chosenAbility, store, abilities);

        }

    }

}
