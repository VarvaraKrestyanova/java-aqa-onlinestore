package com.issoft.auto.store.chain;

public enum AvailableAction {
    PRINT (1),
    SORT (2),
    TOP (3),
    ORDER (4),
    QUIT (5);

    private int actionNumber;

    AvailableAction(int actionNumber){
        this.actionNumber = actionNumber;
    }

    public int getActionNumber() {
        return actionNumber;
    }
}
