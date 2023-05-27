package com.example.pufflemafia.app.data;

import android.util.Log;

import com.example.pufflemafia.app.Event;

import java.util.Vector;

public class GameSetup {
    public int numberOfPlayers() {return names.size();}
    public Vector<String> names;
    public Vector<Role> chosenRoles;
    public boolean isValid;

    public Event<Boolean> onDataUpdated;

    public GameSetup(){
        //this.numberOfPlayers = 0;
        this.names = new Vector<String>();
        this.chosenRoles = new Vector<Role>();
        this.isValid = false;
        this.onDataUpdated = new Event<Boolean>();
    }

    public void SetUpRandomGame(Vector<String> names){
        this.reset();

        this.names = names;
        int numberOfPlayers = this.names.size();

        chosenRoles.add(DataManager.GetRole("Mafia"));
        if(numberOfPlayers > 1){
            for (int i = 0; i < (numberOfPlayers - 1); i++) {
                chosenRoles.add(DataManager.GetRandomRole());
            }
        }
    }

    public void reset(){
        //this.numberOfPlayers = 0;
        this.names.clear();
        this.chosenRoles.clear();
        this.isValid = false;
    }

    public void addRole(Role role){
        this.chosenRoles.add(role);
        this.onDataUpdated.Invoke();
    }

    public void addMultipleRoles(int amount, Role role){
        for(int i = 0; i < amount; ++i){
            this.chosenRoles.add(role);
        }
        this.onDataUpdated.Invoke();
    }

    public void removeRole(Role role){
        this.chosenRoles.remove(role);
        this.onDataUpdated.Invoke();
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
