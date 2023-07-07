package com.example.pufflemafia.app.game.states;

import com.example.pufflemafia.app.events.IEventListener;
import com.example.pufflemafia.app.game.GameManager;

public class NightSummary {

    public static void Initialize(){
        GameManager.OnGameStateChange.AddListener(new IEventListener<GameManager.gameState>() {
            @Override
            public void Response(GameManager.gameState gameState) {
                if(gameState == GameManager.gameState.NIGHT_SUMMARY){
                    Start();
                }
            }
        });
    }

    public static void Start(){

    }
}
