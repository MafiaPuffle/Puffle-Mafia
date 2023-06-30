package com.example.pufflemafia.app.game;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.pufflemafia.app.Event;
import com.example.pufflemafia.app.data.GameSetup;
import com.example.pufflemafia.app.data.Power;
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
    public static int getCurrentIndexOfEventsAtNight(){return currentIndexOfEventsAtNight;}
    public static Role getCurrentRoleActiveAtNight(){ return currentRoleActiveAtNight; }

    // Managers
    public static ActiveRolesManager activeRolesManager;
    public static PlayerManager playerManager;

    // Events
    public static Event<Boolean> onStartDay;
    public static Event<Boolean> onStartNight;

    // Used for sending warning messages for debugging
    private static Logger logger;

    // Initializes the managers for the game
    public GameManager(){
        activeRolesManager = new ActiveRolesManager();
        playerManager = new PlayerManager();
        nightNumber = 0;
        currentState = GameState.Day;
        currentRoleActiveAtNight = new Role();
        onStartDay = new Event<Boolean>();
        onStartNight = new Event<Boolean>();

        logger = Logger.getLogger(GameManager.class.getName());
        // Set Logger level()
        logger.setLevel(Level.WARNING);
    }

    // Once we have selected number of players, set the player names, and chosen all the
    // roles (duplicates allowed) we start a new game
    public static void StartNewGame(@NonNull GameSetup gameSetup){

        if(!gameSetup.checkIfIsValid()){
            logger.warning("Attempted to setup a game with an INVALID GameSetup class");

            return;
        }

        // Resets the night number
        nightNumber = 0;

        PlayerManager.clearAllAlivePlayers();
        PlayerManager.clearAllDeadPlayers();

        // Randomly shuffle the chosenRoles
        Vector<Role> chosenRoles = gameSetup.getChosenRoles();
        Collections.shuffle(chosenRoles, new Random());

        // Add players to PlayerManager with Roles and Names
        for(int i = 0; i < gameSetup.numberOfPlayers(); ++i){
            PlayerManager.AddPlayer(new Player());
            PlayerManager.EditPlayerName(PlayerManager.PlayerMangerListType.ALIVE, i, gameSetup.names.get(i));
            PlayerManager.EditPlayerRole(PlayerManager.PlayerMangerListType.ALIVE, i, chosenRoles.get(i));
        }


        StartDay();
    }

    // handle going from day to night
    public static void StartNight(){

        nightNumber++;
        currentIndexOfEventsAtNight = 0;

        currentState = GameState.Night;
        PlayerManager.ClearAllNightTokens();
        onStartNight.Invoke();

        // Gets all alive roles and sends the to the ActiveRolesManager
        Vector<Role> allRoles = new Vector<Role>();
        for (Player player: PlayerManager.getAllAlive()) {
            Role role = player.getRole();
            Power power = role.getPower();

            // Filters out one time use powers after they have been used i.e. Wizard, the father, holy spirit, satan, witness
            if(power.getType() == Power.PowerType.ONETIMEUSE && power.checkIfPowerHasBeenUsed() == true){
                continue;
            }else{
                allRoles.add(role);
            }
        }

        currentRoleActiveAtNight = ActiveRolesManager.StartNight(allRoles, nightNumber);
        ActiveRolesManager.ResetPlayerMemory();
        if(currentRoleActiveAtNight == null){
            StartDay();
        }
    }

    // if we are in a night this updates currentActiveRoleAtNight
    //      or sets it to day if no more players are left
    public static void GoToPreviousEventAtNight(){
        if(currentState != GameState.Night) return;
        currentIndexOfEventsAtNight--;
//        PlayerManager.ClearRecentlyUpdatedPlayersQueue();
        if(currentIndexOfEventsAtNight < 0){
            currentIndexOfEventsAtNight = 0;
            BackOutOfANight();
            return;
        }

        currentRoleActiveAtNight = ActiveRolesManager.GetRoleForNight(currentIndexOfEventsAtNight);
    }
    public static void GoToNextEventAtNight(){
        if(currentState != GameState.Night) return;
        currentIndexOfEventsAtNight++;
//        PlayerManager.ClearRecentlyUpdatedPlayersQueue();

        currentRoleActiveAtNight = ActiveRolesManager.GetRoleForNight(currentIndexOfEventsAtNight);
        if(currentRoleActiveAtNight == null) StartDay();
    }

    public static void BackOutOfANight(){
        if(currentState != GameState.Night) return;
        nightNumber--;
        currentRoleActiveAtNight = null;
        Log.d("GameMmanager","Backing out of night");
        //StartDay();
    }

    // handles logic for going to day
    //      Is called by GoToNextEventAtNight() when all players have gone
    public static void StartDay(){

        currentState = GameState.Day;
        onStartDay.Invoke();
    }

    public static void PrintSummary(){
        System.out.print("GameManager Current State\n" +
                " currentState: " + currentState + "\n" +
                " nightNumber: " + nightNumber + "\n" +
                " currentIndexOfActiveRoleAtNight: " + currentIndexOfEventsAtNight + "\n");
        if(currentRoleActiveAtNight == null){
            System.out.print("  Role: NULL\n");
        }
        else{
            currentRoleActiveAtNight.PrintSummary(" ");
        }
    }

}
