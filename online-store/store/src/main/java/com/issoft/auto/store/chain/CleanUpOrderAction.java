package com.issoft.auto.store.chain;

import com.issoft.auto.store.Store;
import com.issoft.auto.store.abilities.Abilities;
import com.issoft.auto.store.abilities.CleanUpTheOrder;

public class CleanUpOrderAction extends Action{
    public CleanUpOrderAction(int actionNumber) {
        super(actionNumber);
    }

    @Override
    public void writeData(Store store, Abilities abilities) {

        CleanUpTheOrder cleanUpTheOrder = new CleanUpTheOrder();
        cleanUpTheOrder.cleanOrderList(OrderAction.purchasedGoods);
        cleanUpTheOrder.start();

    }

    @Override
    public String getCommandName() {
        return "cleanOrder";
    }

}
