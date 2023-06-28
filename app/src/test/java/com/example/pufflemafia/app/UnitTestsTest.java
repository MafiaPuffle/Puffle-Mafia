package com.example.pufflemafia.app;

import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.events.TestingListener;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.ResolvingManager;

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
        allPlayers.add(new Player("Everette", DataManager.getRole("doctor")));
        allPlayers.add(new Player("Min", DataManager.getRole("lover")));
        allPlayers.add(new Player("Ehud", DataManager.getRole("lover")));
        allPlayers.add(new Player("Kevin", DataManager.getRole("cyborg")));
        allPlayers.add(new Player("James", DataManager.getRole("baker")));
        allPlayers.add(new Player("Jacob", DataManager.getRole("jailkeeper")));
        allPlayers.add(new Player("Joana", DataManager.getRole("grannyWithAShotgun")));

        System.out.print("Adding players to the game\n");

        // ADD PLAYERS TO GAME
        for (Player p: allPlayers) {
            PlayerManager.addPlayerToGame(p);
        }

        System.out.print("\nStarting the game\n");

        PlayerManager.PrintSummary();

        // MAKE TARGETS FOR NIGHT 1
        Vector<Player> alertTargets = new Vector<Player>();
        alertTargets.add(PlayerManager.getAllAlivePlayers().get(7));

//        Vector<Player> blockTargets = new Vector<Player>();
//        blockTargets.add(PlayerManager.getAllAlivePlayers().get(0));

        Vector<Player> assimilateTargets = new Vector<Player>();
        assimilateTargets.add(PlayerManager.getAllAlivePlayers().get(2));

        Vector<Player> murderTargets = new Vector<Player>();
        murderTargets.add(PlayerManager.getAllAlivePlayers().get(7));

//        Vector<Player> saveTargets = new Vector<Player>();
//        saveTargets.add(PlayerManager.getAllAlivePlayers().get(1));

        Vector<Player> feedTargets = new Vector<Player>();
        feedTargets.add(PlayerManager.getAllAlivePlayers().get(2));
        feedTargets.add(PlayerManager.getAllAlivePlayers().get(1));

        // PREP ACTIONS FOR NIGHT 1

        Player cyborgPlayer = PlayerManager.getAllAlivePlayers().get(4);
        PlayerManager.prepAction(cyborgPlayer, cyborgPlayer.getRole().getActions().get(0), assimilateTargets);

//        Player jailkeepeerPlayer = PlayerManager.getAllAlivePlayers().get(6);
//        PlayerManager.prepAction(jailkeepeerPlayer, jailkeepeerPlayer.getRole().getActions().get(0), blockTargets);

        Player grannyPlayer = PlayerManager.getAllAlivePlayers().get(7);
        PlayerManager.prepAction(grannyPlayer, grannyPlayer.getRole().getActions().get(0), alertTargets);

        Player mafiaPlayer = PlayerManager.getAllAlivePlayers().get(0);
        PlayerManager.prepAction(mafiaPlayer, mafiaPlayer.getRole().getActions().get(0), murderTargets);

//        Player doctorPlayer = PlayerManager.getAllAlivePlayers().get(1);
//        PlayerManager.prepAction(doctorPlayer, doctorPlayer.getRole().getActions().get(0), saveTargets);

        Player bakerPlayer = PlayerManager.getAllAlivePlayers().get(5);
        PlayerManager.prepAction(bakerPlayer, bakerPlayer.getRole().getActions().get(0), feedTargets);

        ResolvingManager.resolveEndOfNightActions();
        ResolvingManager.resolveDelayedActions();

        System.out.print("\nAfter one night the game is in this state\n");

        PlayerManager.PrintSummary();
        ResolvingManager.PrintSummary();
    }

}