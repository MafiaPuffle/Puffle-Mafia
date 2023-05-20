package com.example.pufflemafia;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.Token;
import com.example.pufflemafia.app.game.ActiveRolesManager;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Arrays;
import java.util.Vector;

public class AppUnitTests {

    AppManager appManager;

    @Test
    public void TestAppManager() {
        Initialization();
        Valid_StartGame();

        System.out.print("\nStarting an example game with the following state\n");
        GameManager.PrintSummary();
        PlayerManager.PrintSummary();

        String nameBefore = PlayerManager.allAlive.get(2).name;
        PlayerManager.EditPlayerName(PlayerManager.PlayerMangerListType.ALIVE, 2, "Typhoon");

        System.out.print("\nThe User changed " + nameBefore + "'s name to Typhoon\n");
        PlayerManager.PrintSummary();

        Role roleBefore = new Role(PlayerManager.allAlive.get(3).getRole());
        Role newRole = DataManager.GetRole("Doggie");
        PlayerManager.EditPlayerRole(PlayerManager.PlayerMangerListType.ALIVE, 3, newRole);

        System.out.print("\nThe User changed " + PlayerManager.allAlive.get(3).name + "'s role from " + roleBefore.getName() + " to " + newRole.getName() + "\n");
        PlayerManager.PrintSummary();


        StartNight();
        System.out.print("\nStarting Night " + GameManager.getNightNumber() + "\n");
        while(GameManager.getCurrentRoleActiveAtNight() != null){
            System.out.print(" " + GameManager.getCurrentRoleActiveAtNight().getName() + " who do you wanna " + GameManager.getCurrentRoleActiveAtNight().getPower().getPrompt() + "\n");

            int max = PlayerManager.numberOfPlayersAlive() - 1;
            int min = 0;
            int indexOfTargetPlayer = (int) ((Math.random() * (max - min)) + min);
            UseAbilityAtNight(indexOfTargetPlayer);

            System.out.print("  The " + GameManager.getCurrentRoleActiveAtNight().getName() + " used their ability on " + PlayerManager.allAlive.get(indexOfTargetPlayer).name + "\n");
            GameManager.GoToNextEventAtNight();
        }

        System.out.print("\nAfter Night " + GameManager.getNightNumber() + " the game is in this state\n");
        GameManager.PrintSummary();
        PlayerManager.PrintSummary();

        PlayerManager.AddTokenToPlayer(PlayerManager.PlayerMangerListType.ALIVE, 0, DataManager.GetToken("Doctor"));
        System.out.print("\nThe User gave " + PlayerManager.allAlive.get(0).name + " the Doctor Token\n");
        PlayerManager.PrintSummary();

        PlayerManager.KillPlayer(PlayerManager.allAlive.get(2));
        System.out.print("\nThe User killed " + PlayerManager.allDead.get(0).name);
        PlayerManager.PrintSummary();

        StartNight();
        System.out.print("\nStarting Night " + GameManager.getNightNumber() + "\n");
        while(GameManager.getCurrentRoleActiveAtNight() != null){
            System.out.print(" " + GameManager.getCurrentRoleActiveAtNight().getName() + " who do you wanna " + GameManager.getCurrentRoleActiveAtNight().getPower().getPrompt() + "\n");

            int max = PlayerManager.numberOfPlayersAlive() - 1;
            int min = 0;
            int indexOfTargetPlayer = (int) ((Math.random() * (max - min)) + min);
            UseAbilityAtNight(indexOfTargetPlayer);

            System.out.print("  The " + GameManager.getCurrentRoleActiveAtNight().getName() + " used their ability on " + PlayerManager.allAlive.get(indexOfTargetPlayer).name + "\n");
            GameManager.GoToNextEventAtNight();
        }

        System.out.print("\nAfter Night " + GameManager.getNightNumber() + " the game is in this state\n");
        GameManager.PrintSummary();
        PlayerManager.PrintSummary();

        String nameOfRevived = PlayerManager.allDead.get(0).name;
        PlayerManager.RevivePlayer(PlayerManager.allDead.get(0));
        System.out.print("\nThe User revived " + nameOfRevived + "\n");
        PlayerManager.PrintSummary();
    }

    public void Initialization(){
        appManager = new AppManager();
    }
    public void Test_Initialization(){
        System.out.print("Testing: Initializing AppManager=========\n");

        Initialization();

        assertEquals(AppManager.AppState.MaimMenu, AppManager.getCurrentAppState());

        System.out.print("  Testing: AppManager.gameSetup Initialized Correctly\n");

        assertEquals(0,AppManager.gameSetup.numberOfPlayers);
        assertNotNull(AppManager.gameSetup.names);
        assertNotNull(AppManager.gameSetup.chosenRoles);
        assertFalse(AppManager.gameSetup.isValid);

        System.out.print("  PASSED: AppManager.gameSetup Initialized Correctly\n");

        System.out.print("  Testing: GameManager Initialized Correctly\n");

        assertNotNull(GameManager.activeRolesManager);
        assertNotNull(GameManager.playerManager);
        assertEquals(0, GameManager.getNightNumber());
        assertEquals(GameManager.GameState.Day, GameManager.getCurrentState());
        assertNotNull(GameManager.getCurrentRoleActiveAtNight());
        assertEquals("PuffleName", GameManager.getCurrentRoleActiveAtNight().getName());
        assertNotNull(GameManager.onStartDay);
        assertNotNull(GameManager.onStartNight);

        System.out.print("  PASSED: GameManager Initialized Correctly\n");

        System.out.print("  Testing: DataManager Initialized Correctly\n");

        assertEquals(5, DataManager.allTokens.size());
        assertEquals(5, DataManager.GetAllTokens().size());
        assertEquals(9, DataManager.allPowers.size());
        assertEquals(9, DataManager.GetAllPowers().size());
        assertEquals(9, DataManager.allRoles.size());
        assertEquals(9, DataManager.GetAllRoles().size());

        System.out.print("  PASSED: DataManager Initialized Correctly\n");

        System.out.print("  Testing: ScreenManager Initialized Correctly\n");

        //TODO: Unit tests for ScreenManager

        System.out.print("  PASSED: ScreenManager Initialized Correctly\n");

        System.out.print("PASSED: Initializing AppManager\n");
    }

    public void Valid_GameSetUp(){
        AppManager.gameSetup.reset();

        AppManager.gameSetup.numberOfPlayers = 5;
        String[] ValidNames = {"Hiccup", "Clipper", "Potion Bubble", "Stamper", "Panther Growl"};
        AppManager.gameSetup.names.addAll(Arrays.asList(ValidNames));
        Role[] ValidRoles = {DataManager.GetRole("Mafia"),
                DataManager.GetRole("Detective"),
                DataManager.GetRole("Doctor"),
                DataManager.GetRole("Cupid")};
        AppManager.gameSetup.addMultipleRoles(2, ValidRoles[0]);
        for(int i = 1; i < ValidRoles.length; ++i){
            AppManager.gameSetup.chosenRoles.add(ValidRoles[i]);
        }
    }

    public void Test_Valid_GameSetUp(){
        System.out.print("  Testing: Valid Case\n");

        Valid_GameSetUp();

        assertTrue(AppManager.gameSetup.checkIfIsValid());
        assertEquals(5,AppManager.gameSetup.numberOfPlayers);
        assertEquals(5,AppManager.gameSetup.names.size());
        assertEquals(5,AppManager.gameSetup.chosenRoles.size());

        System.out.print("  PASSED: Valid Case\n");
    }

    public void Invalid_GameSetup(){
        AppManager.gameSetup.reset();

        AppManager.gameSetup.numberOfPlayers = 2;
        String[] InValidNames = {"Duthroles", "Thamheserlic", "Laalzuc", "Nagi", "Olipan"};
        AppManager.gameSetup.names.addAll(Arrays.asList(InValidNames));
        Role[] InValidRoles = {DataManager.GetRole("Mafia"),
                DataManager.GetRole("Detective"),
                DataManager.GetRole("President"),
                DataManager.GetRole("Village Idiot"),
                DataManager.GetRole("Lovers")};
        AppManager.gameSetup.addMultipleRoles(2, InValidRoles[0]);
        for(int i = 1; i < InValidRoles.length; ++i){
            AppManager.gameSetup.chosenRoles.add(InValidRoles[i]);
        }
    }

    public void Test_InValid_GameSetUp(){
        System.out.print("  Testing: InValid Case\n");

        Invalid_GameSetup();

        assertFalse(AppManager.gameSetup.checkIfIsValid());
        assertEquals(2,AppManager.gameSetup.numberOfPlayers);
        assertEquals(5,AppManager.gameSetup.names.size());
        assertEquals(6,AppManager.gameSetup.chosenRoles.size());

        System.out.print("  PASSED: InValid Case\n");
    }

    public void Test_GameSetUp(){
        System.out.print("Testing: Setting Up A Game=========\n");

        Test_Valid_GameSetUp();

        Test_InValid_GameSetUp();

        System.out.print("Passed: Setting Up A Game\n");
    }

    public void Valid_StartGame(){
        Valid_GameSetUp();
        AppManager.onStartGame();
    }

    public void Test_Valid_StartGame(){
        System.out.print("  Testing: Valid Case\n");

        Valid_StartGame();

        // Checking GameManager values
        assertEquals(0, GameManager.getNightNumber());
        assertEquals(GameManager.GameState.Day, GameManager.getCurrentState());

        // Checking PlayerManager values
        assertEquals(AppManager.gameSetup.numberOfPlayers, PlayerManager.allAlive.size());
        System.out.print("GameSetUp used\n");
        AppManager.gameSetup.PrintSummary();
        System.out.print("PlayerManager After Setup\n");
        PlayerManager.PrintSummary();
    }

    public void InValid_StartGame(){
        Invalid_GameSetup();
        AppManager.onStartGame();
    }

    public void Test_InValid_StartGame(){
        System.out.print("  Testing: InValid Case\n");

        InValid_StartGame();

        assertEquals(AppManager.AppState.RoleSelect, AppManager.getCurrentAppState());

        System.out.print("  PASSED: InValid Case\n");
    }

    public void Test_StartGame(){
        System.out.print("Testing: Starting A New Game ======\n");

        Test_Valid_StartGame();

        Test_InValid_StartGame();

        System.out.print("PASSED: Starting A New Game\n");
    }

    public void StartNight(){
        GameManager.StartNight();
    }

    public void Test_StartNight(){
        System.out.print("  Testing: StartNight\n");
        int correctNightNumber = GameManager.getNightNumber() + 1;

        StartNight();

        assertEquals(correctNightNumber, GameManager.getNightNumber());
        assertEquals(GameManager.GameState.Night, GameManager.getCurrentState());

        //PlayerManager.PrintSummary();
        //ActiveRolesManager.PrintSummary();

        System.out.print("  PASSED: StartNight\n");
    }

    public void GoToNextEventAtNight(){
        GameManager.GoToNextEventAtNight();
    }

    public void Test_GoToNextEventAtNight(){
        System.out.print("Testing: GoToNextEventAtNight\n");

        // Testing state before we move to next event at night
        Role roleBefore = GameManager.getCurrentRoleActiveAtNight();
        //System.out.print("\nBefore Going To Next Event At Night\n");
        //GameManager.PrintSummary();

        GoToNextEventAtNight();

        // Tests if we do go to another night event
        if(GameManager.getCurrentState() != GameManager.GameState.Day){
            assertEquals(GameManager.GameState.Night, GameManager.getCurrentState());
            assertNotNull(GameManager.getCurrentRoleActiveAtNight());
            assertNotEquals(roleBefore, GameManager.getCurrentRoleActiveAtNight());
        }
        // Tests for if we go to day instead
        else{
            assertEquals(GameManager.GameState.Day, GameManager.getCurrentState());
        }
        //System.out.print("\nAfter Going To Next Event At Night\n");
        //GameManager.PrintSummary();
        Role roleAfter = GameManager.getCurrentRoleActiveAtNight();
        String roleBeforeName, roleAfterName;

        if(roleBefore == null) roleBeforeName = "NULL";
        else roleBeforeName = roleBefore.getName();
        if(roleAfter == null) roleAfterName = "NULL";
        else roleAfterName = roleAfter.getName();


        System.out.print("Went from: " + roleBeforeName + " to " + roleAfterName + "\n");


        System.out.print("PASSED: GoToNextEventAtNight\n");
    }

    public void GoToPreviousEventAtNight(){
        GameManager.GoToPreviousEventAtNight();
    }

    public void Test_GoToPreviousEventAtNight(){
        System.out.print("Testing: GoToPreviousEventAtNight\n");

        // Testing state before we move to next event at night
        Role roleBefore = GameManager.getCurrentRoleActiveAtNight();
        //System.out.print("\nBefore Going To Previous Event At Night\n");
        //GameManager.PrintSummary();

        GoToPreviousEventAtNight();

        // Tests if we do go to another night event
        if(GameManager.getCurrentState() != GameManager.GameState.Day){
            assertEquals(GameManager.GameState.Night, GameManager.getCurrentState());
            assertNotNull(GameManager.getCurrentRoleActiveAtNight());
            //assertNotEquals(roleBefore, GameManager.getCurrentRoleActiveAtNight());
        }
        // Tests for if we go to day instead
        else{
            assertEquals(GameManager.GameState.Day, GameManager.getCurrentState());
        }
        //System.out.print("\nAfter Going To Previous Event At Night\n");
        //GameManager.PrintSummary();
        Role roleAfter = GameManager.getCurrentRoleActiveAtNight();
        if(roleAfter != null){
            System.out.print("Went from: " + roleBefore.getName() + " to " + roleAfter.getName() + "\n");
        }
        else{
            System.out.print("Went from: " + roleBefore.getName() + " to NULL\n");
        }

        System.out.print("PASSED: GoToPreviousEventAtNight\n");
    }

    public void UseAbilityAtNight(int indexOfTargetPlayer){
        if(indexOfTargetPlayer >= PlayerManager.allAlive.size()
                || GameManager.getCurrentRoleActiveAtNight() == null)
            return;

        PlayerManager.UseAbilityOnPlayer(GameManager.getCurrentRoleActiveAtNight(),
                PlayerManager.allAlive.elementAt(indexOfTargetPlayer));
    }

    public void UseAbilityAtNight_OnRandomPlayer(){
        int max = PlayerManager.numberOfPlayersAlive() - 1;
        int min = 0;
        int indexOfTargetPlayer = (int) ((Math.random() * (max - min)) + min);
        UseAbilityAtNight(indexOfTargetPlayer);
    }

    public void Test_UseAbilityAtNight(int indexOfTargetPlayer){
        System.out.print("Testing: Using an ability at night\n");
        if(indexOfTargetPlayer >= PlayerManager.numberOfPlayersAlive()){
            System.out.print("WARNING: attempted to Test_UseAbilityAtNight on an invalid player\n");
            return;
        }
        if(GameManager.getCurrentRoleActiveAtNight() == null){
            System.out.print("WARNING: attempted to Test_UseAbilityAtNight when there was no active role for the night\n");
            return;
        }

        int amountOfTokensAppliedBeforeAbilityUsed = PlayerManager.allAlive.elementAt(indexOfTargetPlayer).getAllTokensOnPlayer().size();

        UseAbilityAtNight(indexOfTargetPlayer);

        int expectedAmountOfTokensAppliedAfterAbilityUsed = amountOfTokensAppliedBeforeAbilityUsed + 1;

        assertEquals(expectedAmountOfTokensAppliedAfterAbilityUsed, PlayerManager.allAlive.elementAt(indexOfTargetPlayer).getAllTokensOnPlayer().size());

        //PlayerManager.PrintSummary();
        String activeRoleName = GameManager.getCurrentRoleActiveAtNight().getName();
        String targetPlayerName = PlayerManager.allAlive.elementAt(indexOfTargetPlayer).name;
        System.out.print(activeRoleName + " used there ability on " + targetPlayerName + "\n");

        System.out.print("PASSED: Using an ability at night\n");
    }

    public void Test_UseAbilityAtNight_OnRandomPlayer(){
        int max = PlayerManager.numberOfPlayersAlive() - 1;
        int min = 0;
        int indexOfTargetPlayer = (int) ((Math.random() * (max - min)) + min);
        Test_UseAbilityAtNight(indexOfTargetPlayer);
    }

    public void Night(){
        StartNight();
        for (int i = 0; i < PlayerManager.numberOfPlayersAlive(); i++) {
            UseAbilityAtNight_OnRandomPlayer();
            GoToNextEventAtNight();
        }
    }

    public void Test_Night(){
        System.out.print("Testing: Night=========\n");

        Test_StartNight();
        //StartNight();
        for (int i = 0; i < (PlayerManager.numberOfPlayersAlive() + 1); i++) {
            Test_UseAbilityAtNight_OnRandomPlayer();
            Test_GoToNextEventAtNight();
        }
        PlayerManager.PrintSummary();


        System.out.print("PASSED: Night\n");
    }

    public void Test_EditPlayer(){
        System.out.print("Testing: Editing A Player");

        PlayerManager.PlayerMangerListType listType = PlayerManager.PlayerMangerListType.ALIVE;
        int targetPlayerIndex = 0;
        String newName = "NEW_NAME";
        Role newRole = DataManager.GetRole("Terrorist");
        Token newToken = DataManager.GetToken("Detective");
        Token newToken1 = DataManager.GetToken("Mafia");

        Player playerBefore = PlayerManager.allAlive.get(targetPlayerIndex);
        String nameBefore = playerBefore.name;
        Role roleBefore = new Role(playerBefore.getRole());
        Vector<Token> tokensBefore = new Vector<Token>(playerBefore.getAllTokensOnPlayer());

        System.out.print("Before\n");
        playerBefore.PrintSummary();

        PlayerManager.EditPlayerName(listType, targetPlayerIndex, newName);
        PlayerManager.EditPlayerRole(listType, targetPlayerIndex, newRole);
        PlayerManager.AddTokenToPlayer(listType, targetPlayerIndex, newToken);

        Player playerAfter = PlayerManager.allAlive.get(targetPlayerIndex);
        String nameAfter = playerAfter.name;
        Role roleAfter = new Role(playerBefore.getRole());
        Vector<Token> tokensAfter = new Vector<Token>(playerAfter.getAllTokensOnPlayer());


        System.out.print("After editing: name, role and Adding on token\n");
        playerAfter.PrintSummary();

        assertNotNull(playerBefore);
        assertNotNull(playerAfter);
        assertNotEquals(nameBefore, nameAfter);
        assertNotEquals(roleBefore, roleAfter);
        assertNotEquals(tokensBefore.size(), tokensAfter.size());
        assertEquals(newToken, playerAfter.getAllTokensOnPlayer().get(0));

        PlayerManager.EditPlayerToken(listType, targetPlayerIndex, 0, newToken1);

        System.out.print("After editing on token\n");
        playerAfter.PrintSummary();

        assertEquals(newToken1, playerAfter.getAllTokensOnPlayer().get(0));

        PlayerManager.RemovePlayerToken(listType, targetPlayerIndex, 0);

        System.out.print("After removing token\n");
        playerAfter.PrintSummary();

        assertEquals(0, playerAfter.getAllTokensOnPlayer().size());


        System.out.print("PASSED: Editing A Player");
    }

    public void Test_TokenClearing(){
        System.out.print("Testing: Token Clearing=======\n");

        AppManager.gameSetup.numberOfPlayers = 1;
        AppManager.gameSetup.names.add("TEST");
        AppManager.gameSetup.chosenRoles.add(DataManager.GetRole("Detective"));

        assertTrue(AppManager.gameSetup.checkIfIsValid());

        GameManager.StartNewGame(AppManager.gameSetup);

        System.out.print("Before doing anything\n");

        PlayerManager.PrintSummary();

        PlayerManager.AddTokenToPlayer(PlayerManager.PlayerMangerListType.ALIVE, 0, DataManager.GetToken("Mafia"));
        PlayerManager.AddTokenToPlayer(PlayerManager.PlayerMangerListType.ALIVE, 0, DataManager.GetToken("Cupid"));
        PlayerManager.AddTokenToPlayer(PlayerManager.PlayerMangerListType.ALIVE, 0, DataManager.GetToken("Test Clear On Death"));


        System.out.print("After adding all test tokens\n");
        PlayerManager.PrintSummary();

        GameManager.StartNight();

        System.out.print("After starting night\n");
        PlayerManager.PrintSummary();

        GameManager.GoToPreviousEventAtNight();

        PlayerManager.KillPlayer(PlayerManager.allAlive.get(0));

        System.out.print("After killing player\n");
        PlayerManager.PrintSummary();

        System.out.print("PASSED: Token Clearing\n");
    }

}
