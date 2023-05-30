package com.example.pufflemafia.app.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.pufflemafia.app.Event;

import java.util.Objects;
import java.util.Vector;

public class GameSetup {
    public int numberOfPlayers() {return this.names.size();}
    public int numberOfRolesChosen() {return this.chosenRoles.size();}
    public Vector<String> names;
    private Vector<Role> chosenRoles;
    public Vector<Role> getChosenRoles(){return chosenRoles;}
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
        Log.d("GameSetup","Starting SetUpRandomGame");
        this.reset();

        this.names = names;

        this.addRole(DataManager.GetRole("Mafia"));
        if(this.numberOfPlayers() > 1){
            while(checkIfIsValid() == false){
                addRandomRole();
            }
        }
    }

    public void reset(){
        //this.numberOfPlayers = 0;
        this.names.clear();
        this.chosenRoles.clear();
        this.isValid = false;
    }

    public boolean tryAddRole(Role role){
        Log.d("GameSetup", "Trying to add role: " + role.getName());
        if(checkIfCanAddRole(role)) {
            addRole(role);
            return true;
        }
        else{
            return false;
        }
    }

    public void addRole(@NonNull Role role){
        for (int i = 0; i < role.getMinimumAllowed(); i++) {
            this.chosenRoles.add(role);
            Log.d("GameSetup", "Added role: " + role.getName());
        }
        this.onDataUpdated.Invoke();
    }

    public void addRandomRole(){
        Role randomRole = DataManager.GetRandomRole();

        while(tryAddRole(randomRole) == false){
            randomRole = DataManager.GetRandomRole();
        }
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

    public void removeAllRoles(){
        this.chosenRoles.clear();
    }

    public boolean checkIfCanAddRole(Role roleToCheckFor){
        if(this.chosenRoles.size() == numberOfPlayers()){
            Log.d("GameSetup","Did not add role since number of roles currently equals the number of players");
            return false;
        } else if (roleWouldBeAddedToManyTimes(roleToCheckFor)) {
            return false;
        }
        return true;
    }

    private boolean roleWouldBeAddedToManyTimes(Role roleToCheckFor){
        int amountFound = 0;

        for (Role role: this.chosenRoles) {
            if(Objects.equals(role.getName(), roleToCheckFor.getName())) amountFound++;
        }

        int potentialAmountAfterAdding = amountFound + roleToCheckFor.getMinimumAllowed();

        return potentialAmountAfterAdding > roleToCheckFor.getMaximumAllowed();
    }

    public boolean checkIfIsValid(){
        boolean output = false;

        boolean amountOfRolesEqualsAmountOfPlayers = false;
        if(this.numberOfPlayers() == this.chosenRoles.size()){
            amountOfRolesEqualsAmountOfPlayers = true;
        }

        boolean foundAtleastOneMafia = mafiaHasBeenChosen();

        if(amountOfRolesEqualsAmountOfPlayers && foundAtleastOneMafia) output = true;

        this.isValid = output;
        return output;
    }

    public boolean mafiaHasBeenChosen(){
        for (Role role: chosenRoles) {
            if(Objects.equals(role.getName(), "Mafia") || Objects.equals(role.getName(), "Mafia Rival")) {
                return true;
            }
        }
        return false;
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
