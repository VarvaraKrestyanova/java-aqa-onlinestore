package com.issoft.auto.store.chain;

import com.issoft.auto.store.Store;
import com.issoft.auto.store.abilities.Abilities;
import com.issoft.auto.store.abilities.GenerateOrder;

public class QuitAction extends Action{

    public QuitAction(int actionNumber) {
        super(actionNumber);
    }

    @Override
    public void writeData(Store store, Abilities abilities) {
        GenerateOrder.threadsStarter.finishThread(); //just to try to interact with executor
        abilities.quit();
    }

    @Override
    public String getCommandName(){
        return "quit";
    }
}
