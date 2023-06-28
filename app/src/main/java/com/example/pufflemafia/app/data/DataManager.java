package com.example.pufflemafia.app.data;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.actions.conditions.Condition;
import com.example.pufflemafia.app.data.actions.conditions.Condition_DoInitiatorsNOTHaveEffect;
import com.example.pufflemafia.app.data.actions.conditions.Condition_DoTargetNOTHaveEffect;
import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.data.actions.result.Result_CopyRole;
import com.example.pufflemafia.app.data.actions.result.Result_GiveTargetsEffect;
import com.example.pufflemafia.app.data.actions.result.Result_KillTargets;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.data.effects.Effect_Baker;
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
        setUpStartingEffects();
        setUpAllRoles();
    }

    private static void setUpAllEffects(){
        allEffects = new Hashtable<String, Effect>();

        Effect Saved = new Effect("Saved");
        allEffects.put("Saved",Saved);

        Effect Bread = new Effect("Bread");
        allEffects.put("Bread",Bread);

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

        Condition checkForBreadEffect = new Condition_DoTargetNOTHaveEffect(getEffect("Bread"));
        allConditions.put("checkForBreadEffect",checkForBreadEffect);
    }
    private static void setUpAllResults(){
        allResults = new Hashtable<String, Result>();

        Result mafiaKill = new Result_KillTargets(Result.KillType.MAFIA);
        allResults.put("mafiaKill",mafiaKill);

        Result famineKill = new Result_KillTargets(Result.KillType.FAMINE);
        allResults.put("famineKill",famineKill);

        Result doctorSave = new Result_GiveTargetsEffect(getEffect("Saved"));
        allResults.put("doctorSave",doctorSave);

        Result giveBomb = new Result_GiveTargetsEffect(getEffect("Bomb"));
        allResults.put("giveBomb",giveBomb);

        Result giveBread = new Result_GiveTargetsEffect(getEffect("Bread"));
        allResults.put("giveBread",giveBread);

        Result cupidLinkPlayers = new Result_GiveTargetsEffect(getEffect("Cupid_Linked"));
        allResults.put("cupidLinkPlayers",cupidLinkPlayers);

        Result copyRole = new Result_CopyRole();
        allResults.put("copyRole",copyRole);

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

        Vector<Condition> assimilateConditions = new Vector<Condition>();
        assimilateConditions.add(getCondition("isNotBlocked"));

        Vector<Result> assimilateResults = new Vector<Result>();
        assimilateResults.add(getResult("copyRole"));

        Action assimilate = new Action("Assimilate",
                Action.WhenTOResolve.INSTANT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                assimilateConditions,
                assimilateResults);

        allActions.put("assimilate",assimilate);

        Vector<Condition> graveRobberyConditions = new Vector<Condition>();
        graveRobberyConditions.add(getCondition("isNotBlocked"));

        Vector<Result> graveRobberyResults = new Vector<Result>();
        graveRobberyResults.add(getResult("copyRole"));

        Action graveRobbery = new Action("Grave Robbery",
                Action.WhenTOResolve.INSTANT,
                Action.ValidTargets.ALL_DEAD_PLAYERS,
                graveRobberyConditions,
                graveRobberyResults);

        allActions.put("graveRobbery",graveRobbery);

        Vector<Condition> feedConditions = new Vector<Condition>();
        feedConditions.add(getCondition("isNotBlocked"));

        Vector<Result> feedResults = new Vector<Result>();
        feedResults.add(getResult("giveBread"));

        Action feed = new Action("Feed",
                Action.WhenTOResolve.INSTANT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                feedConditions,
                feedResults);

        allActions.put("feed",feed);
    }

    private static void setUpStartingEffects(){
        Vector<Condition> famineConditions = new Vector<Condition>();
        famineConditions.add(getCondition("isNotBlocked"));
        famineConditions.add(getCondition("checkForBreadEffect"));

        Vector<Result> famineResults = new Vector<Result>();
        famineResults.add(getResult("famineKill"));

        Action famine = new Action("Famine",
                Action.WhenTOResolve.DELAY,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                famineConditions,
                famineResults);

        allActions.put("famine",famine);

        Effect Baker = new Effect_Baker();
        allEffects.put("Baker",Baker);
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

        Vector<Action> cyborgActions = new Vector<Action>();
        cyborgActions.add(getAction("assimilate"));

        Role cyborg = new Role("Cyborg",
                R.drawable.cyborg_puffle,
                Role.Teams.NEUTRAL,
                Role.Alliances.NEUTRAL,
                "Copies players roles",
                "Wins however the role they copy wins",
                "Isn't it amazing what technology can do",
                cyborgActions);

        allRoles.put("cyborg",cyborg);

        Vector<Action> necromancerActions = new Vector<Action>();
        necromancerActions.add(getAction("graveRobbery"));

        Role necromancer = new Role("Necromancer",
                R.drawable.necromancer,
                Role.Teams.MAFIA,
                Role.Alliances.EVIL,
                "Copies dead players roles",
                "Wins however the role they copy wins",
                "RISE!",
                necromancerActions);

        allRoles.put("necromancer",necromancer);

        Vector<Action> bakerActions = new Vector<Action>();
        bakerActions.add(getAction("feed"));

        Vector<Effect> bakerStartingEffects = new Vector<Effect>();
        bakerStartingEffects.add(getEffect("Baker"));

        Role baker = new Role("Baker",
                R.drawable.baker_puffle,
                Role.Teams.TOWN,
                Role.Alliances.GOOD,
                "Gives players bread",
                "Wins if the mafia get voted out",
                "What would this town eat without me?",
                bakerActions,
                bakerStartingEffects);

        allRoles.put("baker",baker);
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
