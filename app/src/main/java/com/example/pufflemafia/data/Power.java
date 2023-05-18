package com.example.pufflemafia.data;

import com.example.pufflemafia.R;

// Handles all data and logic for a single power/ability of a playere
public class Power {
    public enum PowerType {PASSIVE, ACTIVE, CONTINOUS, FIRSTNIGHT, SELFACTIVE};

    private String name;
    public String getName(){return name;}

    private PowerType type;
    public PowerType getType () { return type; }
    private String prompt;
    public String getPrompt () { return  prompt; }

    private Token token;
    public Token getToken(){return token;}

    public  Power (){
        this.name = "power";
        this.type = PowerType.PASSIVE;
        this.prompt = "";
        this.token = new Token("Token", R.drawable.village_idiot_puffle, Token.TokenTypes.CLEAR_NEVER);
    }

    public Power (String name,PowerType type, String prompt){
        this.name = name;
        this.type = type;
        this.prompt = prompt;
    }

    public Power (String name,PowerType type, String prompt, Token token){
        this.name = name;
        this.type = type;
        this.prompt = prompt;
        this.token = new Token(token);
    }

    public Power (Power other){
        this.Copy(other);
    }

    public void Copy (Power other){
        this.name = other.getName();
        this.type = other.getType();
        this.prompt = other.getPrompt();
        this.token = new Token(other.getToken());
    }
}
