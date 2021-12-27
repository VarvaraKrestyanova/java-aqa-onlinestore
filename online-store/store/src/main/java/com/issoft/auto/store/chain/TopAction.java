package com.issoft.auto.store.chain;

import com.issoft.auto.store.Store;
import com.issoft.auto.store.abilities.Abilities;

public class TopAction extends Action{

    public TopAction(int actionNumber) {
        super(actionNumber);
    }

    @Override
    public void writeData(Store store, Abilities abilities) {
        abilities.printTop5ByPriceDesc(store.getAllProducts());
    }
}
