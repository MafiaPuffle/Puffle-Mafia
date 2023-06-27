package com.example.pufflemafia.app;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.actions.DataManager;
import com.example.pufflemafia.app.data.actions.conditions.Condition;
import com.example.pufflemafia.app.data.actions.conditions.Condition_DoInitiatorsNOTHaveEffect;
import com.example.pufflemafia.app.data.actions.conditions.Condition_DoTargetNOTHaveEffect;
import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.data.actions.result.Result_GiveTargetsEffect;
import com.example.pufflemafia.app.data.actions.result.Result_KillTargets;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.data.effects.Effect_Bomb;
import com.example.pufflemafia.app.data.effects.Effect_Linked;
import com.example.pufflemafia.app.events.TestingListener;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.ResolvingManager;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.Vector;

public class UnitTestsTest {

    @Test
    public void Test_SetupAutomation(){

        PlayerManager.Initialize();
        ResolvingManager.Initialize();
        TestingListener.Initialize();
        DataManager.Initialize();

        Player player1 = new Player("Jonathan", DataManager.getRole("mafia"));
        Player player2 = new Player("Everette", DataManager.getRole("doctor"));
        Player player3 = new Player("James", DataManager.getRole("lover"));
        Player player4 = new Player("Jacob", DataManager.getRole("lover"));
        Player player5 = new Player("Min", DataManager.getRole("terrorist"));
        Player player6 = new Player("Ehud", DataManager.getRole("cupid"));

        System.out.print("Adding players to the game\n");

        PlayerManager.addPlayerToGame(player1);
        PlayerManager.addPlayerToGame(player2);
        PlayerManager.addPlayerToGame(player3);
        PlayerManager.addPlayerToGame(player4);
        PlayerManager.addPlayerToGame(player5);
        PlayerManager.addPlayerToGame(player6);

        System.out.print("\nStarting the game\n");

        PlayerManager.PrintSummary();

        Vector<Player> murderTargets = new Vector<Player>();
        murderTargets.add(player4);

        Vector<Player> saveTargets = new Vector<Player>();
        saveTargets.add(player3);

        Vector<Player> plantBombTargets = new Vector<Player>();
        plantBombTargets.add(player1);

        Vector<Player> linkTargets = new Vector<Player>();
        linkTargets.add(player3);
        linkTargets.add(player1);

        PlayerManager.prepAction(player6, DataManager.getAction("matchMake"), linkTargets);
        PlayerManager.prepAction(player1, DataManager.getAction("murder"), murderTargets);
        PlayerManager.prepAction(player2, DataManager.getAction("save"), saveTargets);
        ResolvingManager.resolveEndOfNightActions();

        System.out.print("\nAfter one night the game is in this state\n");

        PlayerManager.PrintSummary();
    }

}