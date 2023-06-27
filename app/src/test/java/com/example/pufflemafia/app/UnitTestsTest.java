package com.example.pufflemafia.app;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.actions.conditions.Condition;
import com.example.pufflemafia.app.data.actions.conditions.Condition_DoInitiatorsNOTHaveEffect;
import com.example.pufflemafia.app.data.actions.conditions.Condition_DoTargetNOTHaveEffect;
import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.data.actions.result.Result_GiveTargetsEffect;
import com.example.pufflemafia.app.data.actions.result.Result_KillTargets;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.data.effects.Effect_Linked;
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

        Effect Saved = new Effect("Saved");
        Effect Blocked = new Effect("Blocked");
        Effect Linked = new Effect_Linked();

        Condition isNotBlocked = new Condition_DoInitiatorsNOTHaveEffect(Blocked);
        Condition checkForSavedEffect = new Condition_DoTargetNOTHaveEffect(Saved);

        Result mafiaKill = new Result_KillTargets(Result.KillType.MAFIA);
        Result doctorSave = new Result_GiveTargetsEffect(Saved);


        Vector<Action> mafiaActions = new Vector<Action>();

        Vector<Condition> murderConditions = new Vector<Condition>();
        murderConditions.add(isNotBlocked);
        murderConditions.add(checkForSavedEffect);
        Vector<Result> murderResults = new Vector<Result>();
        murderResults.add(mafiaKill);

        Action murder = new Action("murder",
                Action.WhenTOResolve.END_OF_NIGHT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                murderConditions,
                murderResults);

        mafiaActions.add(murder);

        Vector<Action> doctorActions = new Vector<Action>();
        Vector<Condition> saveConditions = new Vector<Condition>();
        saveConditions.add(isNotBlocked);
        Vector<Result> saveResults = new Vector<Result>();
        saveResults.add(doctorSave);

        Action save = new Action("Save",
                Action.WhenTOResolve.INSTANT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                saveConditions,
                saveResults);

        doctorActions.add(save);

        Vector<Action> civilianActions = new Vector<Action>();
        Vector<Action> loverActions = new Vector<Action>();
        Vector<Effect> loverStartingEffects = new Vector<Effect>();
        loverStartingEffects.add(Linked);

        Role mafia = new Role("Mafia",
                R.drawable.mafia_puffle,
                Role.Teams.MAFIA,
                Role.Alliances.EVIL,
                "Kills players",
                "Wins if they equal half of all alive players",
                "I like stabbing",
                mafiaActions);

        Role doctor = new Role("Doctor",
                R.drawable.doctor_puffle,
                Role.Teams.TOWN,
                Role.Alliances.GOOD,
                "Saves players",
                "Wins if the Mafia gets voted out",
                "Time for your checkup",
                doctorActions);

        Role civilian = new Role("Civilian",
                R.drawable.civilian_puffles,
                Role.Teams.TOWN,
                Role.Alliances.GOOD,
                "Does nothing",
                "Wins if the Mafia gets voted out",
                "I hope this town stays peacful",
                civilianActions);

        Role lover = new Role("Lover",
                R.drawable.lover_puffle,
                Role.Teams.TOWN,
                Role.Alliances.GOOD,
                "Is in love",
                "Wins if the Mafia gets voted out",
                "Love conquers all",
                loverActions,
                loverStartingEffects);

        Player player1 = new Player("Jonathan", mafia);
        Player player2 = new Player("Everette", doctor);
        Player player3 = new Player("James", lover);
        Player player4 = new Player("Jacob", lover);

        PlayerManager.addPlayerToGame(player1);
        PlayerManager.addPlayerToGame(player2);
        PlayerManager.addPlayerToGame(player3);
        PlayerManager.addPlayerToGame(player4);

        PlayerManager.PrintSummary();

        Vector<Player> murderTargets = new Vector<Player>();
        murderTargets.add(player3);

        Vector<Player> saveTargets = new Vector<Player>();
        saveTargets.add(player1);

        PlayerManager.prepAction(player1, murder, murderTargets);
        PlayerManager.prepAction(player2, save, saveTargets);
        ResolvingManager.resolveEndOfNightActions();

        PlayerManager.PrintSummary();
    }

}