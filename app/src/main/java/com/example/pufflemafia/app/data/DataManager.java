package com.example.pufflemafia.app.data;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.actions.conditions.Condition;
import com.example.pufflemafia.app.data.actions.conditions.Condition_DoInitiatorsNOTHaveEffect;
import com.example.pufflemafia.app.data.actions.conditions.Condition_DoTargetNOTHaveEffect;
import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.data.actions.result.Result_CopyRole;
import com.example.pufflemafia.app.data.actions.result.Result_GiveTargetsEffect;
import com.example.pufflemafia.app.data.actions.result.Result_KillTargets;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.data.effects.Effect_Alert;
import com.example.pufflemafia.app.data.effects.Effect_Baker;
import com.example.pufflemafia.app.data.effects.Effect_Bomb;
import com.example.pufflemafia.app.data.effects.Effect_Linked;
import com.example.pufflemafia.app.data.prompts.OptionsPrompt;
import com.example.pufflemafia.app.data.prompts.PlayerPrompt;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class DataManager {

    private static Map<String, Effect> allEffects;
    private static Map<String, Condition> allConditions;
    private static Map<String, Result> allResults;
    private static Map<String, Action> allActions;
    private static Map<String, Role> allRoles;

    public static void Initialize(){
        setUpAllEffects();
        setUpAllConditions();
        setUpAllResults();
        setUpAllActions();
        setUpStartingEffects();
        setUpAllRoles();
    }

    private static void setUpAllEffects(){
        allEffects = new HashMap<String, Effect>();
        
        Effect Saved = new Effect("Saved", R.drawable.doctor_puffle);
        allEffects.put("Saved",Saved);

        Effect Bread = new Effect("Bread", R.drawable.puffle_o_croissant);
        Bread.setClearType(Effect.EffectClearType.NEVER);
        allEffects.put("Bread",Bread);

        Effect Blocked = new Effect("Blocked", R.drawable.jailkeeper_puffle);
        allEffects.put("Blocked",Blocked);

        Effect Lovers_Linked = new Effect_Linked("Lovers");
        Lovers_Linked.setImageResource(R.drawable.lover_puffle);
        Lovers_Linked.setClearType(Effect.EffectClearType.NEVER);
        allEffects.put("Lovers_Linked",Lovers_Linked);

        Effect Cupid_Linked = new Effect_Linked("Cupid");
        Cupid_Linked.setImageResource(R.drawable.cupid_puffle);
        Cupid_Linked.setClearType(Effect.EffectClearType.NEVER);
        allEffects.put("Cupid_Linked",Cupid_Linked);

        Effect Bomb = new Effect_Bomb();
        Bomb.setClearType(Effect.EffectClearType.NEVER);
        allEffects.put("Bomb",Bomb);
    }
    private static void setUpAllConditions(){
        allConditions = new HashMap<String, Condition>();
        Condition isNotBlocked = new Condition_DoInitiatorsNOTHaveEffect(getEffect("Blocked"));
        allConditions.put("isNotBlocked",isNotBlocked);

        Condition checkForSavedEffect = new Condition_DoTargetNOTHaveEffect(getEffect("Saved"));
        allConditions.put("checkForSavedEffect",checkForSavedEffect);

        Condition checkForBreadEffect = new Condition_DoTargetNOTHaveEffect(getEffect("Bread"));
        allConditions.put("checkForBreadEffect",checkForBreadEffect);
    }
    private static void setUpAllResults(){
        allResults = new HashMap<String, Result>();

        Result mafiaKill = new Result_KillTargets(Result.KillType.MAFIA);
        allResults.put("mafiaKill",mafiaKill);

        Result famineKill = new Result_KillTargets(Result.KillType.FAMINE);
        allResults.put("famineKill",famineKill);

        Result alertKill = new Result_KillTargets(Result.KillType.ALERT);
        allResults.put("alertKill",alertKill);

        Result doctorSave = new Result_GiveTargetsEffect(getEffect("Saved"));
        allResults.put("doctorSave",doctorSave);

        Result jailkeeperBlock = new Result_GiveTargetsEffect(getEffect("Blocked"));
        allResults.put("jailkeeperBlock",jailkeeperBlock);

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
        allActions = new HashMap<String, Action>();

        Vector<Condition> murderConditions = new Vector<Condition>();
        murderConditions.add(getCondition("isNotBlocked"));
        murderConditions.add(getCondition("checkForSavedEffect"));

        Vector<Result> murderResults = new Vector<Result>();
        murderResults.add(getResult("mafiaKill"));

        Action murder = new Action("murder",
                Action.ActionType.EVERY_NIGHT, Action.WhenTOResolve.END_OF_NIGHT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                murderConditions,
                murderResults);

        PlayerPrompt murderPrompt = new PlayerPrompt();
        murderPrompt.set_prompt("WHO DO YOU WANNA KILL");
        murderPrompt.addFilter(PlayerPrompt.PlayerFilterType.ALL_DEAD);

        murder.setPrompt(murderPrompt);

        allActions.put("murder",murder);

        Vector<Condition> saveConditions = new Vector<Condition>();
        saveConditions.add(getCondition("isNotBlocked"));

        Vector<Result> saveResults = new Vector<Result>();
        saveResults.add(getResult("doctorSave"));

        Action save = new Action("Save",
                Action.ActionType.EVERY_NIGHT, Action.WhenTOResolve.INSTANT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                saveConditions,
                saveResults);

        PlayerPrompt savePrompt = new PlayerPrompt();
        savePrompt.set_prompt("WHO DO YOU WANNA SAVE");
        savePrompt.addFilter(PlayerPrompt.PlayerFilterType.ALL_DEAD);

        save.setPrompt(savePrompt);

        allActions.put("save",save);

        Vector<Condition> bombPlantConditions = new Vector<Condition>();
        bombPlantConditions.add(getCondition("isNotBlocked"));

        Vector<Result> bombPlantResults = new Vector<Result>();
        bombPlantResults.add(getResult("giveBomb"));

        Action plantBomb = new Action("Plant Bomb",
                Action.ActionType.ONE_TIME_USE, Action.WhenTOResolve.INSTANT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                bombPlantConditions,
                bombPlantResults);

        PlayerPrompt bombPrompt = new PlayerPrompt();
        bombPrompt.set_prompt("WHO DO YOU WANNA PLANT A BOMB ON?");
        bombPrompt.addFilter(PlayerPrompt.PlayerFilterType.ALL_DEAD);

        plantBomb.setPrompt(bombPrompt);

        allActions.put("plantBomb",plantBomb);

        Vector<Condition> matchMakeConditions = new Vector<Condition>();
        matchMakeConditions.add(getCondition("isNotBlocked"));

        Vector<Result> matchMakeResults = new Vector<Result>();
        matchMakeResults.add(getResult("cupidLinkPlayers"));

        Action matchMake = new Action("Match Make",
                Action.ActionType.ONE_TIME_USE, Action.WhenTOResolve.INSTANT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                matchMakeConditions,
                matchMakeResults);

        PlayerPrompt matchMakePrompt = new PlayerPrompt();
        matchMakePrompt.set_prompt("WHO DO YOU WANNA LINK");
        matchMakePrompt.addFilter(PlayerPrompt.PlayerFilterType.ALL_DEAD);

        matchMake.setPrompt(matchMakePrompt);

        allActions.put("matchMake",matchMake);

        Vector<Condition> assimilateConditions = new Vector<Condition>();
        assimilateConditions.add(getCondition("isNotBlocked"));

        Vector<Result> assimilateResults = new Vector<Result>();
        assimilateResults.add(getResult("copyRole"));

        Action assimilate = new Action("Assimilate",
                Action.ActionType.ONE_TIME_USE, Action.WhenTOResolve.INSTANT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                assimilateConditions,
                assimilateResults);

        PlayerPrompt assimilatePrompt = new PlayerPrompt();
        assimilatePrompt.set_prompt("WHO DO YOU WANNA COPY?");
        assimilatePrompt.addFilter(PlayerPrompt.PlayerFilterType.ALL_DEAD);

        assimilate.setPrompt(assimilatePrompt);

        allActions.put("assimilate",assimilate);

        Vector<Condition> graveRobberyConditions = new Vector<Condition>();
        graveRobberyConditions.add(getCondition("isNotBlocked"));

        Vector<Result> graveRobberyResults = new Vector<Result>();
        graveRobberyResults.add(getResult("copyRole"));

        Action graveRobbery = new Action("Grave Robbery",
                Action.ActionType.ONE_TIME_USE, Action.WhenTOResolve.INSTANT,
                Action.ValidTargets.ALL_DEAD_PLAYERS,
                graveRobberyConditions,
                graveRobberyResults);

        PlayerPrompt graveRobberyPrompt = new PlayerPrompt();
        graveRobberyPrompt.set_prompt("WHO DO YOU WANNA COPY?");
        graveRobberyPrompt.addFilter(PlayerPrompt.PlayerFilterType.ALL_ALIVE);

        graveRobbery.setPrompt(graveRobberyPrompt);

        allActions.put("graveRobbery",graveRobbery);

        Vector<Condition> feedConditions = new Vector<Condition>();
        feedConditions.add(getCondition("isNotBlocked"));

        Vector<Result> feedResults = new Vector<Result>();
        feedResults.add(getResult("giveBread"));

        Action feed = new Action("Feed",
                Action.ActionType.EVERY_NIGHT, Action.WhenTOResolve.INSTANT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                feedConditions,
                feedResults);

        PlayerPrompt feedPrompt = new PlayerPrompt();
        feedPrompt.set_prompt("WHO DO YOU WANNA GIVE BREAD TO?");
        feedPrompt.addFilter(PlayerPrompt.PlayerFilterType.ALL_DEAD);
        feedPrompt.addFilter(PlayerPrompt.PlayerFilterType.SELF);

        feed.setPrompt(feedPrompt);

        allActions.put("feed",feed);

        Vector<Condition> blockConditions = new Vector<Condition>();
        blockConditions.add(getCondition("isNotBlocked"));

        Vector<Result> blockResults = new Vector<Result>();
        blockResults.add(getResult("jailkeeperBlock"));

        Action block = new Action("Block",
                Action.ActionType.EVERY_NIGHT, Action.WhenTOResolve.INSTANT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                blockConditions,
                blockResults);

        PlayerPrompt blockPrompt = new PlayerPrompt();
        blockPrompt.set_prompt("WHO DO YOU WANNA BLOCK?");
        blockPrompt.addFilter(PlayerPrompt.PlayerFilterType.ALL_DEAD);

        block.setPrompt(blockPrompt);

        allActions.put("block",block);

        Vector<Condition> alertKillConditions = new Vector<Condition>();
        alertKillConditions.add(getCondition("isNotBlocked"));

        Vector<Result> alertKillResults = new Vector<Result>();
        alertKillResults.add(getResult("alertKill"));

        Action alertKill = new Action("Alert Kill",
                Action.ActionType.EVERY_NIGHT, Action.WhenTOResolve.END_OF_NIGHT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                alertKillConditions,
                alertKillResults);

        allActions.put("alertKill",alertKill);

        Vector<Condition> trueLoveConditions = new Vector<Condition>();

        Vector<Result> trueLoveResults = new Vector<Result>();

        Action trueLove = new Action("trueLove",
                Action.ActionType.EVERY_NIGHT,
                Action.WhenTOResolve.INSTANT,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                trueLoveConditions,
                trueLoveResults
                );

        OptionsPrompt trueLovePrompt = new OptionsPrompt();
        trueLovePrompt.set_prompt("LOVERS WAKE UP AND LOOK AT EACH OTHER");
        trueLovePrompt.addOption("Continue", null);

        trueLove.setPrompt(trueLovePrompt);

        allActions.put("trueLove",trueLove);
    }

    private static void setUpStartingEffects(){
        Vector<Condition> famineConditions = new Vector<Condition>();
        famineConditions.add(getCondition("isNotBlocked"));
        famineConditions.add(getCondition("checkForBreadEffect"));

        Vector<Result> famineResults = new Vector<Result>();
        famineResults.add(getResult("famineKill"));

        Action famine = new Action("Famine",
                Action.ActionType.EVERY_NIGHT, Action.WhenTOResolve.DELAY,
                Action.ValidTargets.ALL_ALIVE_PLAYERS,
                famineConditions,
                famineResults);

        allActions.put("famine",famine);

        Effect Baker = new Effect_Baker();
        Baker.setClearType(Effect.EffectClearType.NEVER);
        allEffects.put("Baker",Baker);




        Effect Alert = new Effect_Alert();
        allEffects.put("Alert",Alert);

        Result giveSelfAlert = new Result_GiveTargetsEffect(getEffect("Alert"));
        allResults.put("giveSelfAlert",giveSelfAlert);

        Vector<Condition> goOnAlertConditions = new Vector<Condition>();
        goOnAlertConditions.add(getCondition("isNotBlocked"));

        Vector<Result> goOnAlertResults = new Vector<Result>();
        goOnAlertResults.add(getResult("giveSelfAlert"));

        Action goOnAlert = new Action("Go On Alert",
                Action.ActionType.ONE_TIME_USE, Action.WhenTOResolve.INSTANT,
                Action.ValidTargets.SELF,
                goOnAlertConditions,
                goOnAlertResults);

        OptionsPrompt goOnAlertPrompt = new OptionsPrompt();
        goOnAlertPrompt.set_prompt("DO YOU WANNA GO ON ALERT?");
        goOnAlertPrompt.addOption("Yes", null);
        goOnAlertPrompt.addOption("No", null);

        allActions.put("goOnAlert",goOnAlert);
    }
    private static void setUpAllRoles(){
        allRoles = new HashMap<String, Role>();

        Vector<Action> mafiaActions = new Vector<Action>();
        mafiaActions.add(getAction("murder"));

        Role mafia = new Role("Mafia",
                2.0f, R.drawable.mafia_puffle,
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
                3.0f, R.drawable.doctor_puffle,
                Role.Teams.TOWN,
                Role.Alliances.GOOD,
                "Saves players",
                "Wins if the Mafia gets voted out",
                "Time for your checkup",
                doctorActions);

        allRoles.put("doctor",doctor);

        Vector<Action> civilianActions = new Vector<Action>();

        Role civilian = new Role("Civilian",
                -1.0f, R.drawable.civilian_puffles,
                Role.Teams.TOWN,
                Role.Alliances.GOOD,
                "Does nothing",
                "Wins if the Mafia gets voted out",
                "I hope this town stays peacful",
                civilianActions);

        allRoles.put("civilian",civilian);

        Vector<Action> loverActions = new Vector<Action>();
        loverActions.add(getAction("trueLove"));
        Vector<Effect> loverStartingEffects = new Vector<Effect>();
        loverStartingEffects.add(getEffect("Lovers_Linked"));

        Role lover = new Role("Lover",
                0.25f, R.drawable.lover_puffle,
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
                0.5f, R.drawable.terrorist_puffle,
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
                0.5f, R.drawable.cupid_puffle,
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
                0.0f, R.drawable.cyborg_puffle,
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
                4.25f, R.drawable.necromancer,
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
                3.0f, R.drawable.baker_puffle,
                Role.Teams.TOWN,
                Role.Alliances.GOOD,
                "Gives players bread",
                "Wins if the mafia get voted out",
                "What would this town eat without me?",
                bakerActions,
                bakerStartingEffects);

        allRoles.put("baker",baker);

        Vector<Action> jailkeeperActions = new Vector<Action>();
        jailkeeperActions.add(getAction("block"));

        Role jailkeeper = new Role("Jailkeeper",
                1.9f, R.drawable.jailkeeper_puffle,
                Role.Teams.MAFIA,
                Role.Alliances.EVIL,
                "Keeps players from using their abilities",
                "Wins if the mafia equal half of the alive players",
                "A night in the cell should cheer you up",
                jailkeeperActions);

        allRoles.put("jailkeeper",jailkeeper);

        Vector<Action> grannyWithAShotgunActions = new Vector<Action>();
        grannyWithAShotgunActions.add(getAction("goOnAlert"));

        Role grannyWithAShotgun = new Role("Granny with a Shotgun",
                1.91f, R.drawable.grandma_puffle,
                Role.Teams.TOWN,
                Role.Alliances.GOOD,
                "Goes on alert once per game, when on alert she kills any player that interacts with her",
                "Wins if the mafia get voted out",
                "Get off my lawn!",
                grannyWithAShotgunActions);

        allRoles.put("grannyWithAShotgun",grannyWithAShotgun);
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
    public static Role getRandomRole(){
        Vector<Role> allRoles = getAllRoles();

        // Generate a random index
        Random random = new Random();
        int randomIndex = random.nextInt(allRoles.size());

        // Retrieve the random role from the allRoles vector
        return allRoles.get(randomIndex);
    }
    public static Vector<Role> getAllRoles(){
        Vector<Role> output = new Vector<Role>();

        for (Map.Entry<String,Role> entry: allRoles.entrySet()) {
            output.add(entry.getValue());
        }

        return output;
    }

}
