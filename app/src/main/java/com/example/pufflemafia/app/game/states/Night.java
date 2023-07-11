package com.example.pufflemafia.app.game.states;

import android.util.Log;

import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.data.prompts.Prompt;
import com.example.pufflemafia.app.events.IEventListener;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.PromptsManager;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Vector;

public class Night {

    public static void Initialize(){
        GameManager.OnGameStateChange.AddListener(new IEventListener<GameManager.gameState>() {
            @Override
            public void Response(GameManager.gameState gameState) {
                if(gameState == GameManager.gameState.NIGHT){
                    Start();
                }
            }
        });
    }

    public static void Start(){
        PlayerManager.clearAllEffectsOfType(Effect.EffectClearType.ON_NIGHT);
        PromptsManager.Start(PlayerManager.getAllAlivePlayers());
    }
}
