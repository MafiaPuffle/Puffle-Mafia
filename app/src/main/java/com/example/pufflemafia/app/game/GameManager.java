package com.example.pufflemafia.app.game;

import com.example.pufflemafia.app.events.Event;

public class GameManager {

    public enum gameState {SETUP, DAY, DELIBERATIONS, ACCUSATIONS, TRIAL, VOTING, NIGHT, NIGHT_SUMMARY}

    public static Event<gameState> OnGameStateChange;
    private static gameState currentGameState;
    public static gameState getCurrentGameState() {
        return currentGameState;
    }
    public static void setCurrentGameState(gameState currentGameState) {
        GameManager.currentGameState = currentGameState;
        OnGameStateChange.Invoke(currentGameState);
    }

    public static void Initialize(){
        OnGameStateChange = new Event<gameState>();

        setCurrentGameState(gameState.SETUP);
    }
}
