package com.example.pufflemafia.app.data.prompts;

import java.util.Vector;

public class PlayerPrompt extends Prompt{

    public enum PlayerFilterType {ALL_DEAD, ALL_ALIVE, SELF}

    private Vector<PlayerFilterType> filters;

    public Vector<PlayerFilterType> getFilters() {
        return filters;
    }

    public void addFilter(PlayerFilterType filter){
        filters.add(filter);
    }

    public PlayerPrompt(){
        filters = new Vector<PlayerFilterType>();
    }
}
