package com.example.pufflemafia.app.data.actions.result;

import com.example.pufflemafia.app.data.actions.Action;

public class Result {

    public enum KillType {MAFIA, VOTE, FAMINE}

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void trigger(Action action){

    }
}
