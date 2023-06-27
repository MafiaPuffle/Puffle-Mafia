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

        // MAKE PLAYERS
        Vector<Player> allPlayers = new Vector<Player>();
        allPlayers.add(new Player("Jonathan", DataManager.getRole("mafia")));
        allPlayers.add(new Player("Everette", DataManager.getRole("terrorist")));
        allPlayers.add(new Player("James", DataManager.getRole("necromancer")));

        System.out.print("Adding players to the game\n");

        // ADD PLAYERS TO GAME
        for (Player p: allPlayers) {
            PlayerManager.addPlayerToGame(p);
        }

        System.out.print("\nStarting the game\n");

        PlayerManager.PrintSummary();

        // MAKE TARGETS FOR NIGHT 1
        Vector<Player> murderTargets = new Vector<Player>();
        murderTargets.add(PlayerManager.getAllAlivePlayers().get(1));

        Vector<Player> plantBombTargets = new Vector<Player>();
        plantBombTargets.add(PlayerManager.getAllAlivePlayers().get(0));

        // PREP ACTIONS FOR NIGHT 1
//        Player terroristPlayer = PlayerManager.getAllAlivePlayers().get(1);
//        PlayerManager.prepAction(terroristPlayer, terroristPlayer.getRole().getActions().get(0), plantBombTargets);

        Player mafiaPlayer = PlayerManager.getAllAlivePlayers().get(0);
        PlayerManager.prepAction(mafiaPlayer, mafiaPlayer.getRole().getActions().get(0), murderTargets);

        ResolvingManager.resolveEndOfNightActions();

        System.out.print("\nAfter one night the game is in this state\n");

        PlayerManager.PrintSummary();

        System.out.print("\nStarting Night 2\n");

        // MAKE TARGETS FOR NIGHT 2
        Vector<Player> graveRobberyTargets = new Vector<Player>();
        graveRobberyTargets.add(PlayerManager.getAllDeadPlayers().get(0));

        // PREP ACTIONS FOR NIGHT 2
        Player necromancerPlayer = PlayerManager.getAllAlivePlayers().get(1);
        PlayerManager.prepAction(necromancerPlayer, necromancerPlayer.getRole().getActions().get(0), graveRobberyTargets);

        ResolvingManager.resolveEndOfNightActions();

        System.out.print("\nAfter the second night the game is in this state\n");

        PlayerManager.PrintSummary();
    }

}