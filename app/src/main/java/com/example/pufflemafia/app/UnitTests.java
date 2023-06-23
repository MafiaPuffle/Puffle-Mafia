package com.example.pufflemafia.app;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.actions.conditions.Condition;
import com.example.pufflemafia.app.data.actions.conditions.Condition_DoInitiatorsHaveEffect;
import com.example.pufflemafia.app.data.actions.conditions.Condition_DoInitiatorsNOTHaveEffect;
import com.example.pufflemafia.app.data.actions.conditions.Condition_DoTargetNOTHaveEffect;
import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.data.actions.result.Result_GiveTargetsEffect;
import com.example.pufflemafia.app.data.actions.result.Result_KillTargets;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.ResolvingManager;

import java.util.Vector;

public class UnitTests {

    public void Test_SetupAutomation(){

        Effect Saved = new Effect();
        Effect Blocked = new Effect();

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

        Role mafia = new Role("Mafia",
                R.drawable.mafia_puffle,
                "Kills players",
                "I like stabbing",
                mafiaActions);

        Role doctor = new Role("Doctor",
                R.drawable.doctor_puffle,
                "Saves players",
                "Time for your checkup",
                doctorActions);

        Role civilian = new Role("Civilian",
                R.drawable.civilian_puffles,
                "Does nothing",
                "I hope this town stays peacful",
                civilianActions);

        Player player1 = new Player("Jonathan", mafia);
        Player player2 = new Player("Everette", doctor);
        Player player3 = new Player("James", civilian);

        PlayerManager.Initialize();
        ResolvingManager.Initialize();
    }
}
