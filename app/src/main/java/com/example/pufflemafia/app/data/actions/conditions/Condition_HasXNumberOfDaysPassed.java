package com.example.pufflemafia.app.data.actions.conditions;

import com.example.pufflemafia.app.data.actions.Action;

public class Condition_HasXNumberOfDaysPassed extends Condition{

    private boolean hasBeenStarted;
    private int startingDay;
    private int currentDay;
    private int amountOfDaysToWait;

    public Condition_HasXNumberOfDaysPassed(int amountOfDaysToWait){
        super("Has X Number of Days Passed");

        this.amountOfDaysToWait = amountOfDaysToWait;

        hasBeenStarted = false;
    }

    @Override
    public boolean check(Action action){
        if(!hasBeenStarted){
            startingDay = 0;
            hasBeenStarted = true;
        }
        currentDay = 1;
        if(currentDay == (startingDay + amountOfDaysToWait)){
            return true;
        }
        return false;
    }
}
