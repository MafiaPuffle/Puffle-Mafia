package com.example.pufflemafia;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.game.ActiveRolesManager;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Arrays;

public class AppUnitTests {
    // TODO: App Unit Tests

    AppManager appManager;

    @Test
    public void TestAppManager() {
        Initialization();
        Valid_StartGame();
        Test_Night();
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
        String[] testValidNames = {"Hiccup", "Clipper", "Potion Bubble", "Stamper", "Panther Growl"};
        AppManager.gameSetup.names.addAll(Arrays.asList(testValidNames));
        Role[] testValidRoles = {DataManager.GetRole("Mafia"),
                DataManager.GetRole("Detective"),
                DataManager.GetRole("Doctor"),
                DataManager.GetRole("Cupid")};
        AppManager.gameSetup.addMultipleRoles(2, testValidRoles[0]);
        for(int i = 1; i < testValidRoles.length; ++i){
            AppManager.gameSetup.chosenRoles.add(testValidRoles[i]);
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
        String[] testInValidNames = {"Duthroles", "Thamheserlic", "Laalzuc", "Nagi", "Olipan"};
        AppManager.gameSetup.names.addAll(Arrays.asList(testInValidNames));
        Role[] testInValidRoles = {DataManager.GetRole("Mafia"),
                DataManager.GetRole("Detective"),
                DataManager.GetRole("President"),
                DataManager.GetRole("Village Idiot"),
                DataManager.GetRole("Lovers")};
        AppManager.gameSetup.addMultipleRoles(2, testInValidRoles[0]);
        for(int i = 1; i < testInValidRoles.length; ++i){
            AppManager.gameSetup.chosenRoles.add(testInValidRoles[i]);
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

    public void Test_Night(){
        System.out.print("Testing: Night=========\n");

        //Test_StartNight();
        StartNight();
        Test_GoToPreviousEventAtNight();
        Test_GoToPreviousEventAtNight();
        Test_GoToPreviousEventAtNight();
        Test_GoToNextEventAtNight();
        Test_GoToNextEventAtNight();
        Test_GoToNextEventAtNight();
        Test_GoToNextEventAtNight();
        Test_GoToNextEventAtNight();

        System.out.print("PASSED: Night\n");
    }

}
