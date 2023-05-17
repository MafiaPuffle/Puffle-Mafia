package com.example.pufflemafia;

import java.util.Collections;


// Handles all the game logic
// Should be the root program for all game logic
public class GameManager {
    enum GameState {MainMenu, RoleSelection, Day, Night}
    public static int nightNumber;
    public static GameState currentState;
    public RolesManager rolesManager;
    public PlayerManager playerManager;

    // Initializes the managers for the game
    public GameManager(){
        this.rolesManager = new RolesManager();
        this.playerManager = new PlayerManager();
        currentState = GameState.MainMenu;
    }

    // Once we have selected available roles and number of mafia we use this to set up a new game
    public void StartNewGame(int numberOfPlayers, int numberOfMafia){

        // Resets the night number
        nightNumber = 0;

        // Adds a bunch of empty players to the playerManager
        this.playerManager.NewGame(numberOfPlayers);

        // Mixes up the selected roles, so we get randomized roles
        Collections.shuffle(this.rolesManager.selectedRoles);

        // Assigns the roles to the players
        for(int i = 0; i < numberOfPlayers; ++i){
            Player player = this.playerManager.allAlive.get(i);
            if(i < numberOfMafia){
                // set player role as mafia
                player.setRole("Mafia");
            }
            else{
                // set player role as a selected role
                player.setRole(this.rolesManager.selectedRoles.get(i));
            }
            this.playerManager.allAlive.setElementAt(player, i);
        }

        currentState = GameState.Day;
    }

    // handle going from day to night
    public void GoToNight(){

        nightNumber++;

        currentState = GameState.Night;

        this.playerManager.StartNight();
    }

    public void GoToDay(){
        //TODO: figure out how to handle going from night to day
    }
}
