package com.example.pufflemafia.app;

import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.ResolvingManager;
import com.example.pufflemafia.app.game.states.Accusations;
import com.example.pufflemafia.app.game.states.Day;
import com.example.pufflemafia.app.game.states.Night;
import com.example.pufflemafia.app.game.states.NightSummary;
import com.example.pufflemafia.app.game.states.Setup;
import com.example.pufflemafia.app.game.states.Trial;
import com.example.pufflemafia.app.game.states.Voting;

public class AppManager {

    public static void Initialize(){
        PlayerManager.Initialize();

        GameManager.Initialize();
        Setup.Initialize();
        Day.Initialize();
        Accusations.Initialize();
        Trial.Initialize();
        Voting.Initialize();
        Night.Initialize();
        NightSummary.Initialize();


        ResolvingManager.Initialize();
        DataManager.Initialize();

        GameManager.setCurrentGameState(GameManager.gameState.SETUP);
    }
}
