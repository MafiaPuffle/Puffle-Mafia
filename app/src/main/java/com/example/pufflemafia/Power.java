package com.example.pufflemafia;

// Handles all data and logic for a single power/ability of a playere
public class Power {
    enum PowerType {PASSIVE, ACTIVE, CONTINOUS, FISTNIGHT, SELFACTIVE};
    private PowerType type;
    public PowerType getType () { return type; }
    private String prompt;
    public String getPrompt () { return  prompt; }

    public  Power (){
        this.type = PowerType.PASSIVE;
        this.prompt = "hug";
    }

    public Power (PowerType type, String prompt){
        Initialize(type, prompt);
    }

    public void Initialize (PowerType type, String prompt){
        this.type = type;
        this.prompt = prompt;
    }

    public void Copy (Power other){
        this.Initialize( other.getType(), other.getPrompt() );
    }
}
