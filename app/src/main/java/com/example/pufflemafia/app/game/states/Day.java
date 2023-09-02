package com.example.pufflemafia.app.game.states;

import android.util.Log;

import com.example.pufflemafia.app.events.IEventListener;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.SoundManager;

public class Day {

    public static void Initialize(){
        GameManager.OnGameStateChange.AddListener(new IEventListener<GameManager.gameState>() {
            @Override
            public void Response(GameManager.gameState gameState) {
                if(gameState == GameManager.gameState.DAY){
                    Start();
                }
            }
        });
    }

    public static void Start(){
        Log.d("DAY", "Start()");
        SoundManager.playSong("Light");
    }
}
