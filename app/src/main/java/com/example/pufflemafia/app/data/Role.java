package com.example.pufflemafia.app.data;

import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.effects.Effect;

import java.util.Vector;

// Handles all data and logic for a single role
public class Role {

    public enum Teams {TOWN, MAFIA, RIVAL_MAFIA, SELF, NEUTRAL}
    public enum Alliances {GOOD, EVIL, NEUTRAL}

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

    private Teams team;
    public Teams getTeam() {
        return team;
    }
    public void setTeam(Teams team) {
        this.team = team;
    }

    private Alliances alliance;
    public Alliances getAlliance() {
        return alliance;
    }
    public void setAlliance(Alliances alliance) {
        this.alliance = alliance;
    }

    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    private String winDescription;
    public String getWinDescription() {
        return winDescription;
    }
    public void setWinDescription(String winDescription) {
        this.winDescription = winDescription;
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
    public boolean hasAction(Action action){
        if(actions.contains(action)) return true;
        else return false;
    }

    private Vector<Effect> startingEffects;
    public Vector<Effect> getStartingEffects() {
        return startingEffects;
    }
    public void setStartingEffects(Vector<Effect> startingEffects) {
        this.startingEffects = startingEffects;
    }

    public Role(String name, int imageResource, Teams team, Alliances alliance, String description, String winDescription, String flavorText, Vector<Action> actions, Vector<Effect> startingEffects){
        setName(name);
        setImageResource(imageResource);
        setTeam(team);
        setAlliance(alliance);
        setDescription(description);
        setWinDescription(winDescription);
        setFlavorText(flavorText);
        setActions(actions);
        setStartingEffects(startingEffects);
    }

    public Role(String name, int imageResource, Teams team, Alliances alliance, String description, String winDescription, String flavorText, Vector<Action> actions){
        setName(name);
        setImageResource(imageResource);
        setTeam(team);
        setAlliance(alliance);
        setDescription(description);
        setWinDescription(winDescription);
        setFlavorText(flavorText);
        setActions(actions);
        startingEffects = new Vector<Effect>();
    }
}

