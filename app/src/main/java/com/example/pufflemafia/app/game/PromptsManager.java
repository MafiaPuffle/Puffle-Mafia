package com.example.pufflemafia.app.game;

import android.util.Log;

import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.prompts.Prompt;
import com.example.pufflemafia.app.events.Event;
import com.example.pufflemafia.app.events.VoidEvent;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;
import java.util.Vector;

public class PromptsManager {

    private static Queue<Prompt> prompts;
    public static Prompt currentPrompt;
    private static Vector<Player> allActivePlayers;
    public static Player currentPlayer;
    private static int currentPlayerIndex;
    public static Action currentAction;

    public static Event<Prompt> OnUpdatePrompt;
    public static VoidEvent OnEndAllPrompts;
    private static int currentActionIndex;

    public static void Initialize(){
        OnUpdatePrompt = new Event<Prompt>();
        OnEndAllPrompts = new VoidEvent();
    }

    public static void Start(Player player, Action action){
        prompts = new ArrayDeque<Prompt>();
        allActivePlayers = new Vector<>();
        allActivePlayers.add(player);

        currentPlayer = player;
        currentAction = action;

        currentPlayerIndex = 0;
        currentActionIndex = 0;

        if(ShouldActionBePrompted(action)){
            QuePrompt(action.getPrompt());
        }
    }

    public static void Start(Vector<Player> activePlayers){
        prompts = new ArrayDeque<Prompt>();
        allActivePlayers = activePlayers;

        Collections.sort(allActivePlayers, new SortPlayByPriority());

        currentPlayerIndex = 0;
        currentPlayer = allActivePlayers.get(currentPlayerIndex);
        currentActionIndex = 0;
        currentAction = currentPlayer.getRole().getActions().get(currentActionIndex);
        if(ShouldActionBePrompted(currentAction)){
            QuePrompt(currentAction.getPrompt());
        }

//        GetNextPrompt();
    }

    public static void QuePrompt(Prompt prompt){
        prompts.add(prompt);
    }

    public static void GetNextPrompt(){
        Log.d("CustomPromptScreen", "Get Next Prompt()");
        currentPrompt = prompts.peek();
        if(currentPrompt == null){
            Log.d("Night","current prompt is NULL");
            FindNextValidAction();
            return;
        }

        OnUpdatePrompt.Invoke(currentPrompt);

        if(prompts.size() > 0){
            prompts.remove();
        }
    }

    public static void FindNextValidAction(){
//        if(currentActionIndex == -1 || currentPlayerIndex == -1){
//            return;
//        }

        Log.d("CustomPromptScreen", "Find Next Valid Action()");
        currentActionIndex++;

        while (currentActionIndex < currentPlayer.getRole().getActions().size()){
            currentAction = currentPlayer.getRole().getActions().get(currentActionIndex);

            if(ShouldActionBePrompted(currentAction)){
                QuePrompt(currentAction.getPrompt());
                GetNextPrompt();
                return;
            }

            currentActionIndex++;
        }

        currentPlayerIndex++;
        if (currentPlayerIndex < allActivePlayers.size()){
            currentActionIndex = -1;
            currentPlayer = allActivePlayers.get(currentPlayerIndex);
            FindNextValidAction();
            return;
        }

        OnEndAllPrompts.Invoke();
    }

    private static boolean ShouldActionBePrompted(Action action){

        Action.ActionType type = action.getActionType();

        switch (type){
            case EVERY_NIGHT:
                return true;
            case ONE_TIME_USE:
                if(action.hasBeenUsedOnce){
                    return false;
                }
                else return true;
        }

        return true;
    }
}
