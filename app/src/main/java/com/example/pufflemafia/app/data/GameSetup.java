package com.example.pufflemafia.app.data;

import java.util.Vector;

public class GameSetup {
    public int numberOfPlayers;
    public Vector<String> names;
    public Vector<Role> chosenRoles;
    public boolean isValid;

    public GameSetup(){
        this.numberOfPlayers = 0;
        this.names = new Vector<String>();
        this.chosenRoles = new Vector<Role>();
        this.isValid = false;
    }

    public void reset(){
        this.numberOfPlayers = 0;
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

        if(this.numberOfPlayers == this.names.size()){
            if(this.names.size() == this.chosenRoles.size()){
                output = true;
            }
        }

        this.isValid = output;
        return output;
    }


}
