package com.example.pufflemafia;

import android.media.Image;

import androidx.annotation.NonNull;

public class Role {
    enum Alliances {GOOD, EVIL}
    enum Teams {TOWN, MAFIA, SELF, RIVAL_MAFIA}
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
    // TODO: figure out how to store images
    //private Image image;
    //public Image getImage (){ return image; }

    public Role(){
        this.priority = 1.0f;
        this.alliance = Alliances.GOOD;
        this.team = Teams.TOWN;
        this.name = "PuffleName";
        //this.image = new Image();
    }

    public Role (float priority, Alliances alliance, Teams team, @NonNull Power power, String name){
        Initialize(priority, alliance, team, power.getType(), power.getPrompt(), name);
    }

    public Role (float priority, Alliances alliance, Teams team, Power.PowerType powerType, String powerPromt, String name){
        Initialize(priority, alliance, team, powerType, powerPromt, name);

    }

    public void Initialize (float priority, Alliances alliance, Teams team, Power.PowerType powerType, String powerPrompt, String name){
        this.priority = priority;
        this.alliance = alliance;
        this.team = team;
        this.power.Initialize(powerType, powerPrompt);
        this.name = name;
    }

    public void Copy (@NonNull Role other){
        this.Initialize(other.priority, other.alliance, other.team, other.getPower().getType(), other.getPower().getPrompt(), other.name);
    }
}

