package com.example.pufflemafia.data;

import androidx.annotation.NonNull;

import com.example.pufflemafia.R;

import java.util.Comparator;

// Handles all data and logic for a single role
public class Role {
    // Enums
    public enum Alliances {GOOD, EVIL, NEUTRAL}
    public enum Teams {TOWN, MAFIA, SELF, RIVAL_MAFIA, NEUTRAL}

    // properties

    private String name;
    public String getName (){ return name; }

    private int imageResource;
    public int getImageResource (){ return imageResource; }

    private float priority;
    public float getPriority (){ return priority; }

    private Alliances alliance;
    public Alliances getAlliance (){ return alliance; }

    private Teams team;
    public Teams getTeam (){ return team; }

    private Power power = new Power();
    public Power getPower() { return power; }

    private int minimumAllowed;
    public int getMinimumAllowed(){return minimumAllowed;}
    private int maximumAllowed;
    public int getMaximumAllowed(){return maximumAllowed;}


    public Role(){
        this.priority = 1;
        this.alliance = Alliances.GOOD;
        this.team = Teams.TOWN;
        this.name = "PuffleName";
        this.imageResource = R.drawable.alien_puffle;
        this.minimumAllowed = 1;
        this.maximumAllowed = 3;
    }

    public Role(Role other){
        this.Copy(other);
    }

    // Constructor with Power class as an argument
    public Role ( String name, int imageResource, float priority, Alliances alliance, Teams team, @NonNull Power power, int minimumAllowed, int maximumAllowed){
        this.name = name;
        this.imageResource = imageResource;
        this.priority = priority;
        this.alliance = alliance;
        this.team = team;
        this.power = new Power(power);
        this.minimumAllowed = minimumAllowed;
        this.maximumAllowed = maximumAllowed;
    }

    // Lets us copy values from another role into this
    public void Copy (@NonNull Role other){
        this.name = other.getName();
        this.imageResource = other.getImageResource();
        this.priority = other.getImageResource();
        this.alliance = other.getAlliance();
        this.team = other.getTeam();
        this.power = new Power(other.getPower());
        this.minimumAllowed = other.minimumAllowed;
        this.maximumAllowed = other.maximumAllowed;
    }
}

