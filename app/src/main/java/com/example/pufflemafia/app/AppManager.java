package com.example.pufflemafia.app;

import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.GameSetup;
import com.example.pufflemafia.app.game.GameManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AppManager {
    public enum AppState {MaimMenu, RoleSelect, Game}
    private static AppState currentAppState;
    public static AppState getCurrentAppState(){return currentAppState;}

    public static GameSetup gameSetup;

    public static GameManager gameManager;
    public static DataManager dataManager;
    public static ScreenManager screenManager;

    // Used for sending warning messages for debugging
    private static Logger logger;

    public AppManager(){
        currentAppState = AppState.MaimMenu;
        gameSetup = new GameSetup();

        gameManager = new GameManager();
        dataManager = new DataManager();
        screenManager = new ScreenManager();

        logger = Logger.getLogger(AppManager.class.getName());
        // Set Logger level()
        logger.setLevel(Level.WARNING);

        onMainMenu();
    }


    public static void onMainMenu(){
        currentAppState = AppState.MaimMenu;
    }

    public static void onGoToRoleSelect(){
        currentAppState = AppState.RoleSelect;

        // role select logic
    }

    public static void onStartGame(){
        if(!gameSetup.checkIfIsValid()){
            logger.warning("Attempted to start a game with an INVALID GameSetup class\n" +
                                "Returning to RoleSelectState");

            onGoToRoleSelect();
            return;
        }

        currentAppState = AppState.Game;

        GameManager.StartNewGame(gameSetup);
    }

}
