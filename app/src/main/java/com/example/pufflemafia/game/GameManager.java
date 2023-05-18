package com.example.pufflemafia.game;

import com.example.pufflemafia.Event;
import com.example.pufflemafia.RolesManager;
import com.example.pufflemafia.data.Role;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;


// Handles all the game logic
// Should be the root program for all game logic
public class GameManager {
    // Game State Properties
    public enum GameState {Day, Night}
    private static GameState currentState;
    public static GameState getCurrentState(){ return currentState; }

    // Night Properties
    private static int nightNumber;
    public static int getNightNumber(){ return nightNumber; }
    private static Role currentRoleActiveAtNight;
    private static int currentIndexOfEventsAtNight;
    public static Role getCurrentRoleActiveAtNight(){ return currentRoleActiveAtNight; }

    // Managers
    public static ActiveRolesManager activeRolesManager;
    public static PlayerManager playerManager;

    // Events
    public static Event<Integer> onStartDay;
    public static Event<Integer> onStartNight;

    // Initializes the managers for the game
    public GameManager(){
        activeRolesManager = new ActiveRolesManager();
        playerManager = new PlayerManager();
        nightNumber = 0;
        currentState = GameState.Day;
        currentRoleActiveAtNight = new Role();
        onStartDay = new Event<Integer>();
        onStartNight = new Event<Integer>();
    }

    // Once we have selected number of players, set the player names, and chosen all the
    // roles (duplicates allowed) we start a new game
    public void StartNewGame(int numberOfPlayers, Vector<String> playerNames, Vector<Role> chosenRoles){

        // Resets the night number
        nightNumber = 0;

        PlayerManager.allAlive.clear();
        PlayerManager.allDead.clear();

        // Randomly shuffle the chosenRoles
        Collections.shuffle(chosenRoles, new Random());

        // Add players to PlayerManager with Roles and Names
        for(int i = 0; i < numberOfPlayers; ++i){
            PlayerManager.AddPlayer(new Player());
            PlayerManager.EditPlayerName(PlayerManager.PlayerMangerListType.ALIVE, i, playerNames.get(i));
            PlayerManager.EditPlayerRole(PlayerManager.PlayerMangerListType.ALIVE, i, chosenRoles.get(i));
        }


        StartDay();
    }

    // handle going from day to night
    public void StartNight(){

        nightNumber++;
        currentIndexOfEventsAtNight = 0;

        currentState = GameState.Night;
        onStartNight.Invoke();

        // Gets all alive roles and sends the to the ActiveRolesManager
        Vector<Role> allAliveRoles = new Vector<Role>();
        for (Player player: PlayerManager.allAlive) {
            allAliveRoles.add(player.getRole());
        }

        ActiveRolesManager.StartNight(allAliveRoles, nightNumber);
    }

    // if we are in a night this updates currentActiveRoleAtNight
    //      or sets it to day if no more players are left
    public void GoToPreviousEventAtNight(){
        if(currentState != GameState.Night) return;
        currentIndexOfEventsAtNight--;
        if(currentIndexOfEventsAtNight < 0) currentIndexOfEventsAtNight = 0;

        currentRoleActiveAtNight = ActiveRolesManager.GetRoleForNight(currentIndexOfEventsAtNight);
    }
    public void GoToNextEventAtNight(){
        if(currentState != GameState.Night) return;
        currentIndexOfEventsAtNight++;

        currentRoleActiveAtNight = ActiveRolesManager.GetRoleForNight(currentIndexOfEventsAtNight);
        if(currentRoleActiveAtNight == null) StartDay();
    }

    // handles logic for going to day
    //      Is called by GoToNextEventAtNight() when all players have gone
    public void StartDay(){

        currentState = GameState.Day;
        onStartDay.Invoke();
    }

}
