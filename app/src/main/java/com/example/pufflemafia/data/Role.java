package com.example.pufflemafia.data;

import androidx.annotation.NonNull;

import com.example.pufflemafia.R;

import java.util.Comparator;

// Handles all data and logic for a single role
public class Role {
    // Enums
    enum Alliances {GOOD, EVIL, NEUTRAL}
    enum Teams {TOWN, MAFIA, SELF, RIVAL_MAFIA, NEUTRAL}

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


    public Role(){
        this.priority = 1;
        this.alliance = Alliances.GOOD;
        this.team = Teams.TOWN;
        this.name = "PuffleName";
        this.imageResource = R.drawable.alien_puffle;
    }

    public Role(Role other){
        this.Copy(other);
    }

    // Constructor with Power class as an argument
    public Role ( String name, int imageResource, float priority, Alliances alliance, Teams team, @NonNull Power power){
        this.name = name;
        this.imageResource = imageResource;
        this.priority = priority;
        this.alliance = alliance;
        this.team = team;
        this.power = new Power(power);
    }

    // Lets us copy values from another role into this
    public void Copy (@NonNull Role other){
        this.name = other.getName();
        this.imageResource = other.getImageResource();
        this.priority = other.getImageResource();
        this.alliance = other.getAlliance();
        this.team = other.getTeam();
        this.power = new Power(other.getPower());
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

