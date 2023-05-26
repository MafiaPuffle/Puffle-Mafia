package com.example.pufflemafia.app.data;

import androidx.annotation.NonNull;

import com.example.pufflemafia.R;

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


    private String description;
    public String getDescription(){return description;}


    public Role(){
        this.priority = 1;
        this.alliance = Alliances.GOOD;
        this.team = Teams.TOWN;
        this.name = "PuffleName";
        this.imageResource = R.drawable.alien_puffle;
        this.description = "Does something amazing!";
    }

    public Role(Role other){
        this.Copy(other);
    }

    // Constructor with Power class as an argument
    public Role ( String name, int imageResource, float priority, Alliances alliance, Teams team, @NonNull Power power, String description){
        this.name = name;
        this.imageResource = imageResource;
        this.priority = priority;
        this.alliance = alliance;
        this.team = team;
        this.power = new Power(power);
        this.description = description;
    }

    // Lets us copy values from another role into this
    public void Copy (@NonNull Role other){
        this.name = other.getName();
        this.imageResource = other.getImageResource();
        this.priority = other.getPriority();
        this.alliance = other.getAlliance();
        this.team = other.getTeam();
        this.power = new Power(other.getPower());
        this.description = other.getDescription();
    }

    public void PrintSummary(){
        System.out.print("Role: " + this.name + "\n");
        this.power.PrintSummary("    ");
    }

    public void PrintSummary(String spacer){
        System.out.print(spacer + "Role: " + this.name + "\n");
        this.power.PrintSummary(spacer + "    ");
    }

    public void PrintDetailed(){
        System.out.print("Role\n");
        System.out.print("Name: " + this.name + "\n"
                        + "     ImageResource: " + this.imageResource + "\n"
                        + "     Priority: " + this.priority + "\n"
                        + "     Alliance: " + this.alliance + "\n"
                        + "     Team: " + this.team + "\n"
                        + "     Image Resource: " + this.imageResource + "\n"
                        + "     Description: " + this.getDescription() + "\n");
        this.power.PrintDetailed("    ");
    }

    public void PrintDetailed(String spacer){
        System.out.print(spacer + "Role\n");
        System.out.print(spacer + "     Name: " + this.name + "\n"
                        + spacer + "     ImageResource: " + this.imageResource + "\n"
                        + spacer + "     Priority: " + this.priority + "\n"
                        + spacer + "     Alliance: " + this.alliance + "\n"
                        + spacer + "     Team: " + this.team + "\n"
                        + spacer + "     Image Resource: " + this.imageResource + "\n"
                        + spacer + "     Description: " + this.getDescription() + "\n");
        this.power.PrintDetailed(spacer + "    ");
    }
}

