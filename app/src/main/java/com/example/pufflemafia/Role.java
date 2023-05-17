package com.example.pufflemafia;

import android.media.Image;

import androidx.annotation.NonNull;

import java.util.Comparator;

// Handles all data and logic for a single role
public class Role {
    // Enums
    enum Alliances {GOOD, EVIL, NEUTRAL}
    enum Teams {TOWN, MAFIA, SELF, RIVAL_MAFIA, NEUTRAL}

    // properties
    private float priority;
    public float getPriority (){ return priority; }

    private Alliances alliance;
    public Alliances getAlliance (){ return alliance; }

    private Teams team;
    public Teams getTeam (){ return team; }

    private Power power = new Power();
    public Power getPower() { return power; }

    private String name;
    public String getName (){ return name; }

    private int imageResource;
    public int getImageResource (){ return imageResource; }


    public Role(){
        this.priority = 1;
        this.alliance = Alliances.GOOD;
        this.team = Teams.TOWN;
        this.name = "PuffleName";
        this.imageResource = R.drawable.alien_puffle;
    }

    // Constructor with Power class as an argument
    public Role (float priority, Alliances alliance, Teams team, @NonNull Power power, String name, int imageResource){
        Initialize(priority, alliance, team, power.getType(), power.getPrompt(), name, imageResource);
    }

    // Constructor taking Power in its components instead
    public Role (float priority, Alliances alliance, Teams team, Power.PowerType powerType, String powerPrompt, String name, int imageResource){
        Initialize(priority, alliance, team, powerType, powerPrompt, name, imageResource);

    }

    // Called by all constructors to set up this class
    public void Initialize (float priority, Alliances alliance, Teams team, Power.PowerType powerType, String powerPrompt, String name, int imageResource){
        this.priority = priority;
        this.alliance = alliance;
        this.team = team;
        this.power.Initialize(powerType, powerPrompt);
        this.name = name;
        this.imageResource = imageResource;
    }

    // Lets us copy values from another role into this
    public void Copy (@NonNull Role other){
        this.Initialize(other.priority, other.alliance, other.team, other.getPower().getType(), other.getPower().getPrompt(), other.name, other.getImageResource());
    }
}

// Helper class used to sort roles by their priority
class SortByPriority implements Comparator<Role> {
    public int compare(Role a, Role b)
    {
        if(a.getPriority() < b.getPriority()) return -1;
        else if (a.getPriority() == b.getPriority()) return 0;
        else return 1;
    }
}

