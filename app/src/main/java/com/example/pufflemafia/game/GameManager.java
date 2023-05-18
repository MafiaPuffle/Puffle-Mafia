package com.example.pufflemafia.game;

import com.example.pufflemafia.Event;
import com.example.pufflemafia.RolesManager;
import com.example.pufflemafia.data.Role;

import java.util.Collections;


// Handles all the game logic
// Should be the root program for all game logic
public class GameManager {
    // Game State Properties
    enum GameState {MainMenu, RoleSelection, Day, Night}
    private static GameState currentState;
    public static GameState getCurrentState(){ return currentState; }

    // Night Properties
    private static int nightNumber;
    public static int getNightNumber(){ return nightNumber; }
    private Player currentPlayerActiveAtNight;
    public Player getCurrentPlayerActiveAtNight(){ return currentPlayerActiveAtNight; }

    // Managers
    //public RolesManager rolesManager;
    public PlayerManager playerManager;

    // Events
    public Event<Integer> onStartDay;
    public Event<Integer> onStartNight;

    // Initializes the managers for the game
    public GameManager(){
        //this.rolesManager = new RolesManager();
        this.playerManager = new PlayerManager();
        nightNumber = 0;
        currentState = GameState.MainMenu;
        //this.winningTeam = Role.Teams.TOWN;
        currentPlayerActiveAtNight = new Player();
        this.onStartDay = new Event<Integer>();
        this.onStartNight = new Event<Integer>();
        //this.onGameWon = new Event<Role.Teams>();
        this.currentPlayerActiveAtNight = new Player();
    }

    // Sets the current state to Role Selection
    public void GoToRoleSelectScreen(){
        currentState = GameState.RoleSelection;
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
                player.setRole(this.rolesManager.selectedRoles.get(i - numberOfMafia));
            }
            this.playerManager.allAlive.setElementAt(player, i);
        }

        currentState = GameState.Day;
    }

    // handle going from day to night
    public void StartNight(){

        nightNumber++;

        currentState = GameState.Night;
        this.onStartNight.Invoke();

        this.playerManager.StartNight();
    }

    // if we are in a night this updates currentActivePlayerAtNight
    //      or sets it to day if no more players are left
    public void GoToNextEventAtNight(){
        if(currentState != GameState.Night) return;

        currentPlayerActiveAtNight = playerManager.GetNextPlayerForNight();

        if (currentPlayerActiveAtNight == null) StartDay();
    }

    // handles logic for going to day
    //      Is called by GoToNextEventAtNight() when all players have gone
    public void StartDay(){

        currentState = GameState.Day;
        this.onStartDay.Invoke();
    }

    // Handles killing a player
    public void KillPlayer(Player player){
        this.playerManager.KillPlayer(player);
    }

}
