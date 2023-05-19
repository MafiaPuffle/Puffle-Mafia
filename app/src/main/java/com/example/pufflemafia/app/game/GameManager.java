package com.example.pufflemafia.app.game;

import com.example.pufflemafia.app.Event;
import com.example.pufflemafia.app.data.GameSetup;
import com.example.pufflemafia.app.data.Role;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


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

    // Used for sending warning messages for debugging
    private static Logger logger;

    // Initializes the managers for the game
    public GameManager(){
        activeRolesManager = new ActiveRolesManager();
        playerManager = new PlayerManager();
        nightNumber = 0;
        currentState = GameState.Day;
        currentRoleActiveAtNight = new Role();
        onStartDay = new Event<Integer>();
        onStartNight = new Event<Integer>();

        logger = Logger.getLogger(GameManager.class.getName());
        // Set Logger level()
        logger.setLevel(Level.WARNING);
    }

    // Once we have selected number of players, set the player names, and chosen all the
    // roles (duplicates allowed) we start a new game
    public static void StartNewGame(GameSetup gameSetup){

        if(!gameSetup.checkIfIsValid()){
            logger.warning("Attempted to setup a game with an INVALID GameSetup class");

            return;
        }

        // Resets the night number
        nightNumber = 0;

        PlayerManager.allAlive.clear();
        PlayerManager.allDead.clear();

        // Randomly shuffle the chosenRoles
        Collections.shuffle(gameSetup.chosenRoles, new Random());

        // Add players to PlayerManager with Roles and Names
        for(int i = 0; i < gameSetup.numberOfPlayers; ++i){
            PlayerManager.AddPlayer(new Player());
            PlayerManager.EditPlayerName(PlayerManager.PlayerMangerListType.ALIVE, i, gameSetup.names.get(i));
            PlayerManager.EditPlayerRole(PlayerManager.PlayerMangerListType.ALIVE, i, gameSetup.chosenRoles.get(i));
        }


        StartDay();
    }

    // handle going from day to night
    public static void StartNight(){

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
    public static void GoToPreviousEventAtNight(){
        if(currentState != GameState.Night) return;
        currentIndexOfEventsAtNight--;
        if(currentIndexOfEventsAtNight < 0) currentIndexOfEventsAtNight = 0;

        currentRoleActiveAtNight = ActiveRolesManager.GetRoleForNight(currentIndexOfEventsAtNight);
    }
    public static void GoToNextEventAtNight(){
        if(currentState != GameState.Night) return;
        currentIndexOfEventsAtNight++;

        currentRoleActiveAtNight = ActiveRolesManager.GetRoleForNight(currentIndexOfEventsAtNight);
        if(currentRoleActiveAtNight == null) StartDay();
    }

    // handles logic for going to day
    //      Is called by GoToNextEventAtNight() when all players have gone
    public static void StartDay(){

        currentState = GameState.Day;
        onStartDay.Invoke();
    }

}
