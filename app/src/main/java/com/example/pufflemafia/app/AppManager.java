package com.example.pufflemafia.app;

import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.GameSetup;
import com.example.pufflemafia.app.game.GameManager;

public class AppManager {
    public enum AppState {MaimMenu, RoleSelect, Game}
    private static AppState currentAppState;
    public static AppState getCurrentAppState(){return currentAppState;}

    public static GameSetup gameSetup;

    public static GameManager gameManager;
    public static DataManager dataManager;
    public static ScreenManager screenManager;

    public AppManager(){
        currentAppState = AppState.MaimMenu;
        gameSetup = new GameSetup();

        gameManager = new GameManager();
        dataManager = new DataManager();
        screenManager = new ScreenManager();

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
        currentAppState = AppState.Game;

        GameManager.StartNewGame(gameSetup);
    }

}
