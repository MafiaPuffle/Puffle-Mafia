package com.example.pufflemafia;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameManagerUnitTests {

    @Test
    public void testGameManager(){
        GameManager gameManager = new GameManager();
        ListenForWinner listenForWinner = new ListenForWinner();
        gameManager.onGameWon.AddListener(listenForWinner);

        testGame(gameManager, listenForWinner);

        //testSettingUp(gameManager);
        //testStartingNewGame(gameManager);
        //testStartingNewNight(gameManager);
        //testThatAPlayerHasAnAbilityForTheNight(gameManager);
        //testGoingToNextEventAtNight(gameManager);
        //testUsingAbilitesAtNight(gameManager);
        //testThatItWentToDay(gameManager);
        //testKillPlayer(gameManager);

        //testOnWinEvent(gameManager,listenForWinner);
    }

    public void testGame(GameManager gameManager, ListenForWinner listenForWinner){
        testSettingUp(gameManager);
        testStartingNewGame(gameManager);
        while(listenForWinner.aTeamWon == false){
            testStartingNewNight(gameManager);
            testUsingAbilitesAtNight(gameManager);
            testKillPlayer(gameManager);
            testThatItWentToDay(gameManager);
            printOutAliveAndDead(gameManager);
        }

        System.out.print(listenForWinner.winningTeam + " won\n");
        printOutAliveAndDead(gameManager);
    }

    public void testSettingUp(GameManager gameManager){
        assertNotEquals(null, gameManager);
    }


    public void testStartingNewGame(GameManager gameManager){

        gameManager.rolesManager.SelectRole("Cupid");
        gameManager.rolesManager.SelectRole("Cyborg");
        gameManager.rolesManager.SelectRole("Doggie");
        gameManager.rolesManager.SelectRole("Lovers");
        gameManager.rolesManager.SelectRole("Veteran");

        gameManager.StartNewGame(5,1);

        assertEquals(5,gameManager.playerManager.allAlive.size());

        System.out.print("\nAll roles in this game: \n");
        for(int i = 0; i < gameManager.playerManager.allAlive.size(); ++i){
            System.out.print(gameManager.playerManager.allAlive.get(i).getRole().getName() + "\n");
        }

        assertEquals(5,gameManager.playerManager.allAlive.size());
    }


    public void testStartingNewNight(GameManager gameManager){
        gameManager.StartNight();
        assertEquals(GameManager.GameState.Night, GameManager.getCurrentState());
    }

    public void testThatAPlayerHasAnAbilityForTheNight(GameManager gameManager){
        assertNotEquals(null, gameManager.playerManager.GetNextPlayerForNight());
    }


    public void testGoingToNextEventAtNight(GameManager gameManager){

        System.out.print("\nStarting turns at night\n");

        for(int i = 0; i < gameManager.playerManager.allAlive.size(); ++i) {
            gameManager.GoToNextEventAtNight();
            Role role = new Role();
            Player player = gameManager.getCurrentPlayerActiveAtNight();
            if(player != null){
                role.Copy(player.getRole());
                System.out.print(role.getName() + " who do you wanna " + role.getPower().getPrompt() + "\n");
            }
        }
    }

    public void testUsingAbilitesAtNight(GameManager gameManager){
        System.out.print("\nStarting turns at night\n");

        int numberOfPlayersAlive = gameManager.playerManager.allAlive.size();

        for(int i = 0; i <= numberOfPlayersAlive; ++i) {
            gameManager.GoToNextEventAtNight();
            Role role = new Role();
            Player player = gameManager.getCurrentPlayerActiveAtNight();
            if(player != null){
                role.Copy(player.getRole());
                System.out.print(role.getName() + " who do you wanna " + role.getPower().getPrompt() + "\n");

                Integer indexOfTarget = (int)(Math.random() * ((numberOfPlayersAlive - 1)));
                gameManager.playerManager.UseAbilityOnPlayer(player, gameManager.playerManager.allAlive.elementAt(indexOfTarget));
                System.out.print("  " + role.getName() + " used their ability on " + gameManager.playerManager.allAlive.elementAt(indexOfTarget).getRole().getName() + "\n\n");
                assertNotEquals(0, gameManager.playerManager.allAlive.elementAt(indexOfTarget).getAllTokensOnPlayer().size());
            }
        }
    }

    public void testThatItWentToDay(GameManager gameManager){
        assertEquals(GameManager.GameState.Day, GameManager.getCurrentState());

        System.out.print("It is now day\n\n");
    }

    public void testKillPlayer(GameManager gameManager){
        Integer numberOfPlayersAlive = gameManager.playerManager.allAlive.size();
        Integer numberOfPlayersDead = gameManager.playerManager.allDead.size();
        Integer indexOfTarget = (int)(Math.random() * ((numberOfPlayersAlive - 1)));

        Player player = gameManager.playerManager.allAlive.get(indexOfTarget);
        gameManager.KillPlayer(player);

        assertEquals(numberOfPlayersDead + 1, gameManager.playerManager.allDead.size());
        assertEquals(numberOfPlayersAlive - 1, gameManager.playerManager.allAlive.size());

        System.out.print(player.getRole().getName() + " was killed\n\n");
    }

    public void testOnWinEvent(GameManager gameManager, ListenForWinner listenForWinner){
        gameManager.rolesManager.SelectRole("Doctor");

        gameManager.StartNewGame(2,1);
        gameManager.KillPlayer(gameManager.playerManager.allAlive.get(0));
        gameManager.StartDay();

        assertEquals(true, listenForWinner.aTeamWon);
        assertEquals(Role.Teams.TOWN, listenForWinner.winningTeam);
    }

    public void printOutAliveAndDead(GameManager gameManager){
        System.out.print("Alive:\n");
        for(int i = 0; i < gameManager.playerManager.allAlive.size(); ++i){
            System.out.print(gameManager.playerManager.allAlive.get(i).getRole().getName() + "\n");
        }

        System.out.print("Dead:\n");
        for(int i = 0; i < gameManager.playerManager.allDead.size(); ++i){
            System.out.print(gameManager.playerManager.allDead.get(i).getRole().getName() + "\n");
        }

        System.out.print("\n");
    }

}
