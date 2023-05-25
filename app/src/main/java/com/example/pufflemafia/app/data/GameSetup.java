package com.example.pufflemafia.app.data;

import android.util.Log;

import java.util.Vector;

public class GameSetup {
    public int numberOfPlayers() {return names.size();}
    public Vector<String> names;
    public Vector<Role> chosenRoles;
    public boolean isValid;

    public GameSetup(){
        //this.numberOfPlayers = 0;
        this.names = new Vector<String>();
        this.chosenRoles = new Vector<Role>();
        this.isValid = false;
    }

    public void reset(){
        //this.numberOfPlayers = 0;
        this.names.clear();
        this.chosenRoles.clear();
        this.isValid = false;
    }

    public void addMultipleRoles(int amount, Role role){
        for(int i = 0; i < amount; ++i){
            this.chosenRoles.add(role);
        }
    }

    public boolean checkIfIsValid(){
        boolean output = false;

        boolean amountOfRolesEqualsAmountOfPlayers = false;
        if(this.numberOfPlayers() == this.chosenRoles.size()){
            amountOfRolesEqualsAmountOfPlayers = true;
        }

        boolean foundAtleastOneMafia = false;
        for (Role role: chosenRoles) {
            if(role.getName() == "Mafia") {
                foundAtleastOneMafia = true;
                break;
            }
        }

        if(amountOfRolesEqualsAmountOfPlayers && foundAtleastOneMafia) output = true;

        this.isValid = output;
        return output;
    }

    public void LogSummary(){
        String message = "";

        message += "GameSetup Current state\n";
        message += "  Number of Players: " + this.numberOfPlayers() + "\n";
        message += "  Names: \n";
        for (String name: this.names) {
            message += "      " + name + "\n";
        }
        message += "  Chosen Roles: \n";
        for (Role role: this.chosenRoles){
            message += "      " + role.getName() + "\n";
        }
        message += "  Is Valid: " + this.isValid + "\n";

        Log.d("GameSetup",message);
    }

    public void PrintSummary(){
        System.out.print("GameSetup Current state");
        System.out.print("  Number of Players: " + this.numberOfPlayers() + "\n");
        System.out.print("  Names: \n");
        for (String name: this.names) {
            System.out.print("      " + name + "\n");
        }
        System.out.print("  Chosen Roles: \n");
        for (Role role: this.chosenRoles){
            role.PrintSummary("        ");
            System.out.print("\n");
        }
        System.out.print("  Is Valid: " + this.isValid + "\n");
    }

    public void PrintDetailed(){
        System.out.print("GameSetup Current state");
        System.out.print("  Number of Players: " + this.numberOfPlayers() + "\n");
        System.out.print("  Names: \n");
        for (String name: this.names) {
            System.out.print("      " + name + "\n");
        }
        System.out.print("  Chosen Roles: \n");
        for (Role role: this.chosenRoles){
            role.PrintDetailed("        ");
            System.out.print("\n");
        }
        System.out.print("  Is Valid: " + this.isValid + "\n");
    }


}
