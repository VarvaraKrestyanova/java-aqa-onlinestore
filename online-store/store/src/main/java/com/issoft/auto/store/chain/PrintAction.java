package com.issoft.auto.store.chain;
import com.issoft.auto.store.Store;
import com.issoft.auto.store.abilities.Abilities;

public class PrintAction extends Action{

    public PrintAction(int actionNumber) {
        super(actionNumber);
    }

    @Override
    public void writeData(Store store, Abilities abilities) {
        store.printStoreData();
    }

    @Override
    public String getCommandName(){
        return "print";
    }


}
