package com.example.pufflemafia.app.data.actions.conditions;

import com.example.pufflemafia.app.data.actions.Action;

public class Condition {

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean check(Action action)
    {
        return true;
    }
}
