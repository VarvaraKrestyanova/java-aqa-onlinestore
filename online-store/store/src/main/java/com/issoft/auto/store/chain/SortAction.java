package com.issoft.auto.store.chain;

import com.issoft.auto.store.Store;
import com.issoft.auto.store.abilities.Abilities;

public class SortAction extends Action {

    public SortAction(int actionNumber) {
        super(actionNumber);
    }

    @Override
    public void writeData(Store store, Abilities abilities) {
        abilities.sortAndPrint(store.getAllProducts());
    }

    @Override
    public String getCommandName(){
        return "sort";
    }

}
