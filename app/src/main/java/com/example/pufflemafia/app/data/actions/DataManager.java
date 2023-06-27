package com.example.pufflemafia.app.data.actions;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.actions.conditions.Condition;
import com.example.pufflemafia.app.data.actions.conditions.Condition_DoInitiatorsNOTHaveEffect;
import com.example.pufflemafia.app.data.actions.conditions.Condition_DoTargetNOTHaveEffect;
import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.data.actions.result.Result_GiveTargetsEffect;
import com.example.pufflemafia.app.data.actions.result.Result_KillTargets;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.data.effects.Effect_Bomb;
import com.example.pufflemafia.app.data.effects.Effect_Linked;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Vector;

public class DataManager {

    private static Dictionary<String, Effect> allEffects;
    private static Dictionary<String, Condition> allConditions;
    private static Dictionary<String, Result> allResults;
    private static Dictionary<String, Action> allActions;
    private static Dictionary<String, Role> allRoles;

    public static void Initialize(){
        setUpAllEffects();
        setUpAllConditions();
        setUpAllResults();
        setUpAllActions();
        setUpAllRoles();
    }

    private static void setUpAllEffects(){
        allEffects = new Hashtable<String, Effect>();

        Effect Saved = new Effect("Saved");
        allEffects.put("Saved",Saved);

        Effect Blocked = new Effect("Blocked");
        allEffects.put("Blocked",Blocked);

        Effect Lovers_Linked = new Effect_Linked("Lovers");
        allEffects.put("Lovers_Linked",Lovers_Linked);

        Effect Cupid_Linked = new Effect_Linked("Cupid");
        allEffects.put("Cupid_Linked",Cupid_Linked);

        Effect Bomb = new Effect_Bomb();
        allEffects.put("Bomb",Bomb);
    }
    private static void setUpAllConditions(){
        allConditions = new Hashtable<String, Condition>();
        Condition isNotBlocked = new Condition_DoInitiatorsNOTHaveEffect(getEffect("Blocked"));
        allConditions.put("isNotBlocked",isNotBlocked);
        Condition checkForSavedEffect = new Condition_DoTargetNOTHaveEffect(getEffect("Saved"));
        allConditions.put("checkForSavedEffect",checkForSavedEffect);
    }
    private static void setUpAllResults(){
        allResults = new Hashtable<String, Result>();

        Result mafiaKill = new Result_KillTargets(Result.KillType.MAFIA);
        allResults.put("mafiaKill",mafiaKill);

        Result doctorSave = new Result_GiveTargetsEffect(getEffect("Saved"));
        allResults.put("doctorSave",doctorSave);

        Result giveBomb = new Result_GiveTargetsEffect(getEffect("Bomb"));
        allResults.put("giveBomb",giveBomb);

        Result cupidLinkPlayers = new Result_GiveTargetsEffect(getEffect("Cupid_Linked"));
        allResults.put("cupidLinkPlayers",cupidLinkPlayers);

    }
    private static void setUpAllActions(){
        allActions = new Hashtable<String, Action>();

        Vector<Condition> murderConditions = new Vector<Condition>();
        murderConditions.add(getCondition("isNotBlocked"));
        murderConditions.add(getCondition("checkForSavedEffect"));

        Vector<Result> murderResults = new Vector<Result>();
        murderResults.add(getResult("mafiaKill"));

        Action murder = new Action("murder",
                Action.WhenTOResolve.END_OF_NIGHT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                murderConditions,
                murderResults);

        allActions.put("murder",murder);

        Vector<Condition> saveConditions = new Vector<Condition>();
        saveConditions.add(getCondition("isNotBlocked"));

        Vector<Result> saveResults = new Vector<Result>();
        saveResults.add(getResult("doctorSave"));

        Action save = new Action("Save",
                Action.WhenTOResolve.INSTANT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                saveConditions,
                saveResults);

        allActions.put("save",save);

        Vector<Condition> bombPlantConditions = new Vector<Condition>();
        bombPlantConditions.add(getCondition("isNotBlocked"));

        Vector<Result> bombPlantResults = new Vector<Result>();
        bombPlantResults.add(getResult("giveBomb"));

        Action plantBomb = new Action("Plant Bomb",
                Action.WhenTOResolve.INSTANT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                bombPlantConditions,
                bombPlantResults);

        allActions.put("plantBomb",plantBomb);

        Vector<Condition> matchMakeConditions = new Vector<Condition>();
        matchMakeConditions.add(getCondition("isNotBlocked"));

        Vector<Result> matchMakeResults = new Vector<Result>();
        matchMakeResults.add(getResult("cupidLinkPlayers"));

        Action matchMake = new Action("Match Make",
                Action.WhenTOResolve.INSTANT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                matchMakeConditions,
                matchMakeResults);

        allActions.put("matchMake",matchMake);
    }
    private static void setUpAllRoles(){
        allRoles = new Hashtable<String, Role>();

        Vector<Action> mafiaActions = new Vector<Action>();
        mafiaActions.add(getAction("murder"));

        Role mafia = new Role("Mafia",
                R.drawable.mafia_puffle,
                Role.Teams.MAFIA,
                Role.Alliances.EVIL,
                "Kills players",
                "Wins if they equal half of all alive players",
                "I like stabbing",
                mafiaActions);

        allRoles.put("mafia",mafia);

        Vector<Action> doctorActions = new Vector<Action>();
        doctorActions.add(getAction("save"));

        Role doctor = new Role("Doctor",
                R.drawable.doctor_puffle,
                Role.Teams.TOWN,
                Role.Alliances.GOOD,
                "Saves players",
                "Wins if the Mafia gets voted out",
                "Time for your checkup",
                doctorActions);

        allRoles.put("doctor",doctor);

        Vector<Action> civilianActions = new Vector<Action>();

        Role civilian = new Role("Civilian",
                R.drawable.civilian_puffles,
                Role.Teams.TOWN,
                Role.Alliances.GOOD,
                "Does nothing",
                "Wins if the Mafia gets voted out",
                "I hope this town stays peacful",
                civilianActions);

        allRoles.put("civilian",civilian);

        Vector<Action> loverActions = new Vector<Action>();
        Vector<Effect> loverStartingEffects = new Vector<Effect>();
        loverStartingEffects.add(getEffect("Lovers_Linked"));

        Role lover = new Role("Lover",
                R.drawable.lover_puffle,
                Role.Teams.TOWN,
                Role.Alliances.GOOD,
                "Is in love",
                "Wins if the Mafia gets voted out",
                "Love conquers all",
                loverActions,
                loverStartingEffects);

        allRoles.put("lover",lover);

        Vector<Action> terroristActions = new Vector<Action>();
        terroristActions.add(getAction("plantBomb"));

        Role terrorist = new Role("Terrorist",
                R.drawable.terrorist_puffle,
                Role.Teams.MAFIA,
                Role.Alliances.EVIL,
                "Plants bomb",
                "Wins if the Mafia equals half of all alive players",
                "Time to go out with a bang",
                terroristActions);

        allRoles.put("terrorist",terrorist);

        Vector<Action> cupidActions = new Vector<Action>();
        cupidActions.add(getAction("matchMake"));

        Role cupid = new Role("Cupid",
                R.drawable.cupid_puffle,
                Role.Teams.TOWN,
                Role.Alliances.GOOD,
                "Links two other players",
                "Wins if the Mafia getts voted out",
                "I just love bring people together",
                cupidActions);

        allRoles.put("cupid",cupid);
    }

    public static Effect getEffect(String key){
        return allEffects.get(key);
    }
    public static Condition getCondition(String key){
        return allConditions.get(key);
    }
    public static Result getResult(String key){
        return allResults.get(key);
    }
    public static Action getAction(String key){
        return allActions.get(key);
    }
    public static Role getRole(String key){
        return allRoles.get(key);
    }

}
