package com.example.pufflemafia.app.game.states;

import android.util.Log;

import com.example.pufflemafia.app.data.actions.ActionLog;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.data.prompts.Prompt;
import com.example.pufflemafia.app.events.IEventListener;
import com.example.pufflemafia.app.game.ActionLogManager;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.PromptsManager;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Vector;

public class Night {

    private static int nightNumber;
    public static int getNightNumber(){
        return nightNumber;
    }
    public static void resetNightNumber(){
        nightNumber = 0;
    }

    public static void Initialize(){
        GameManager.OnGameStateChange.AddListener(new IEventListener<GameManager.gameState>() {
            @Override
            public void Response(GameManager.gameState gameState) {
                if(gameState == GameManager.gameState.NIGHT){
                    Start();
                }

                if(gameState == GameManager.gameState.SETUP){
                    resetNightNumber();
                }
            }
        });

        resetNightNumber();
    }

    public static void Start(){
        SoundManager.playSong("Mystery");
        PlayerManager.clearAllEffectsOfType(Effect.EffectClearType.ON_NIGHT);
        PromptsManager.Start(PlayerManager.getAllAlivePlayers());

        nightNumber++;


        ActionLog log = new ActionLog();
        log.addToMessage("=== Night " + nightNumber + " ===");
        ActionLogManager.addLog(log);
    }
}
