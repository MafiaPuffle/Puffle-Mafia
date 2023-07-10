package com.example.pufflemafia.app;

import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.prompts.OptionsPrompt;
import com.example.pufflemafia.app.data.prompts.PlayerPrompt;
import com.example.pufflemafia.app.events.TestingListener;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.PromptsManager;
import com.example.pufflemafia.app.game.ResolvingManager;
import com.example.pufflemafia.app.game.states.Night;

import org.junit.Test;

import java.util.Vector;

public class UnitTestsTest {

    @Test
    public void Test_SetupAutomation(){

//        AppManager.Initialize();

        DataManager.Initialize();
        PlayerManager.Initialize();
        Night.Start();

        PlayerPrompt p = new PlayerPrompt();
        p.addFilter(PlayerPrompt.PlayerFilterType.ALL_DEAD);
        p.addFilter(PlayerPrompt.PlayerFilterType.SELF);

        OptionsPrompt o = new OptionsPrompt();
        o.addOption("yes", p);
        o.addOption("no", p);

        PromptsManager.QuePrompt(o);
        PromptsManager.GetNextPrompt();

        System.out.print("Current night prompt is: " + PromptsManager.currentPrompt + "\n");

        o.chooseOption(0);

        System.out.print("Current night prompt is: " + PromptsManager.currentPrompt + "\n");
    }

}