package com.issoft.auto.store.chain;

import com.issoft.auto.store.Store;
import com.issoft.auto.store.abilities.Abilities;

import java.lang.reflect.Field;

public abstract class Action {

    private int actionNumber;
    private Action nextAction;

    public Action(int actionNumber) {
        this.actionNumber = actionNumber;
    }

    public void setNextAction(Action nextAction) {
        this.nextAction = nextAction;
    }

    public void callTheAction(int request, Store store, Abilities abilities) throws InstantiationException, IllegalAccessException {
        if (request == actionNumber) {
            Field[] declaredFields = ActionNumber.class.getDeclaredFields();
            for (Field field : declaredFields){
                if (field.getInt(field) == request){
                    System.out.println("**********************\nChosen option is " + field.getName() + "\n**********************\n");
                }
            }
            writeData(store, abilities);
        }
        if (nextAction != null) {
            nextAction.callTheAction(request, store, abilities);
        }
    }

    public abstract void writeData(Store store, Abilities abilities) throws IllegalAccessException, InstantiationException;

}
