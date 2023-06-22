package com.example.pufflemafia.app.data;

import android.util.Log;

import com.example.pufflemafia.R;

// Handles all data and logic for a single power/ability of a playere
public class Power {
    public enum PowerType {PASSIVE, ACTIVE, CONTINOUS, FIRSTNIGHT, SELFACTIVE, ONETIMEUSE};
    public enum PowerPromptType {ALL_Alive_PLAYERS, ALL_DEAD_PLAYERS, ALL_PLAYERS, YES_OR_NO, NOTHING}

    private String name;
    public String getName(){return name;}

    private PowerType type;
    public PowerType getType () { return type; }
    private String prompt;
    public String getPrompt () { return  prompt; }

    private PowerPromptType promptType;
    public PowerPromptType getPromptType () {return promptType;}

    private Token token;
    public Token getToken(){return token;}

    private boolean hasPowerBeenUsed;
    public boolean checkIfPowerHasBeenUsed(){return hasPowerBeenUsed;}
    public void usePower(){
        hasPowerBeenUsed = true;
    }

    public  Power (){
        this.name = "power";
        this.type = PowerType.PASSIVE;
        this.prompt = "";
        this.promptType = PowerPromptType.ALL_Alive_PLAYERS;
        this.token = new Token("Token", "will give the player a funny feeling", R.drawable.village_idiot_puffle, Token.TokenTypes.CLEAR_NEVER, 1);
        this.hasPowerBeenUsed = false;
    }

    public Power (String name, PowerType type, String prompt, PowerPromptType promptType){
        this.name = name;
        this.type = type;
        this.prompt = prompt;
        this.promptType = promptType;
        this.token = new Token("Empty", "will give the player a funny feeling", 0, Token.TokenTypes.CLEAR_NEVER, 1);
        this.hasPowerBeenUsed = false;
    }

    public Power (String name, PowerType type, String prompt, Token token, PowerPromptType promptType){
        this.name = name;
        this.type = type;
        this.prompt = prompt;
        this.promptType = promptType;
        this.token = new Token(token);
        this.hasPowerBeenUsed = false;
    }

    public Power (Power other){
        this.Copy(other);
    }

    public void Copy (Power other){
        this.name = other.getName();
        this.type = other.getType();
        this.prompt = other.getPrompt();
        this.promptType = other.getPromptType();
        this.token = new Token(other.getToken());
        this.hasPowerBeenUsed = false;
    }

    public void PrintSummary(){
        System.out.print("Power: " + this.name + "\n");
        this.token.PrintSummary("    ");
    }

    public void PrintSummary(String spacer){
        System.out.print(spacer + "Power: " + this.name + "\n");
        this.token.PrintSummary(spacer + "    ");
    }

    public void LogSummary(String spacer){
        String message = "";
        message += spacer + "Power: " + this.name + "\n";
        message += spacer + " Has been used: " + this.checkIfPowerHasBeenUsed() + "\n";
        Log.d("Power", message);
    }

    public void PrintDetailed(){
        System.out.print("Power:\n");
        System.out.print("    Name: " + this.name + "\n"
                        + "    Type: " + this.type + "\n"
                        + "    Prompt: " + this.prompt);
        this.token.PrintDetailed("    ");
    }

    public void PrintDetailed(String spacer){
        System.out.print(spacer + "Power:\n");
        System.out.print(spacer + "    Name: " + this.name + "\n"
                + spacer + "    Type: " + this.type + "\n"
                + spacer + "    Prompt: " + this.prompt);
        this.token.PrintDetailed(spacer + "    ");
    }
}
