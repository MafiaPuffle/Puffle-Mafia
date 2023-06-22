package com.example.pufflemafia.app.data;

import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.effects.Effect;

import java.util.Vector;

// Handles all data and logic for a single role
public class Role {

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private int imageResource;
    public int getImageResource() {
        return imageResource;
    }
    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    private String flavorText;
    public String getFlavorText() {
        return flavorText;
    }
    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    private Vector<Action> actions;
    public Vector<Action> getActions() {
        return actions;
    }
    public void setActions(Vector<Action> actions) {
        this.actions = actions;
    }

    private Vector<Effect> startingEffects;
    public Vector<Effect> getStartingEffects() {
        return startingEffects;
    }
    public void setStartingEffects(Vector<Effect> startingEffects) {
        this.startingEffects = startingEffects;
    }
}

