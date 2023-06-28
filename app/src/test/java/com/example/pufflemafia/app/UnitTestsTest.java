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

        System.out.print("Adding players to the game\n");

        // ADD PLAYERS TO GAME
        for (Player p: allPlayers) {
            PlayerManager.addPlayerToGame(p);
        }

        System.out.print("\nStarting the game\n");

        PlayerManager.PrintSummary();

        // MAKE TARGETS FOR NIGHT 1
        Vector<Player> assimilateTargets = new Vector<Player>();
        assimilateTargets.add(PlayerManager.getAllAlivePlayers().get(2));

        Vector<Player> murderTargets = new Vector<Player>();
        murderTargets.add(PlayerManager.getAllAlivePlayers().get(5));

        Vector<Player> saveTargets = new Vector<Player>();
        saveTargets.add(PlayerManager.getAllAlivePlayers().get(2));

        Vector<Player> feedTargets = new Vector<Player>();
        feedTargets.add(PlayerManager.getAllAlivePlayers().get(2));
        feedTargets.add(PlayerManager.getAllAlivePlayers().get(1));

        // PREP ACTIONS FOR NIGHT 1

        Player cyborgPlayer = PlayerManager.getAllAlivePlayers().get(4);
        PlayerManager.prepAction(cyborgPlayer, cyborgPlayer.getRole().getActions().get(0), assimilateTargets);

        Player mafiaPlayer = PlayerManager.getAllAlivePlayers().get(0);
        PlayerManager.prepAction(mafiaPlayer, mafiaPlayer.getRole().getActions().get(0), murderTargets);

        Player doctorPlayer = PlayerManager.getAllAlivePlayers().get(1);
        PlayerManager.prepAction(doctorPlayer, doctorPlayer.getRole().getActions().get(0), saveTargets);

        Player bakerPlayer = PlayerManager.getAllAlivePlayers().get(5);
        PlayerManager.prepAction(bakerPlayer, bakerPlayer.getRole().getActions().get(0), feedTargets);

        ResolvingManager.resolveEndOfNightActions();

        System.out.print("\nAfter one night the game is in this state\n");

        PlayerManager.PrintSummary();
        ResolvingManager.PrintSummary();
    }

}