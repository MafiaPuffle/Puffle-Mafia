package com.example.pufflemafia.app.game;

import android.util.Log;

import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.prompts.Prompt;
import com.example.pufflemafia.app.events.VoidEvent;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Vector;

public class PromptsManager {

    private static Queue<Prompt> prompts;
    public static Prompt currentPrompt;
    private static Vector<Player> allActivePlayers;
    public static Player currentPlayer;
    private static int currentPlayerIndex;
    public static Action currentAction;

    public static VoidEvent OnEndAllPrompts;
    private static int currentActionIndex;

    public static void Initialize(){
        OnEndAllPrompts = new VoidEvent();
    }

    public static void Start(Vector<Player> activePlayers){
        prompts = new ArrayDeque<Prompt>();
        allActivePlayers = activePlayers;

        currentPlayerIndex = 0;
        currentPlayer = allActivePlayers.get(currentPlayerIndex);
        currentActionIndex = 0;
        currentAction = currentPlayer.getRole().getActions().get(currentActionIndex);
        if(ShouldActionBePrompted(currentAction)){
            QuePrompt(currentAction.getPrompt());
        }

        GetNextPrompt();
    }

    public static void QuePrompt(Prompt prompt){
        prompts.add(prompt);
    }

    public static void GetNextPrompt(){
        currentPrompt = prompts.peek();
        if(currentPrompt == null){
            Log.d("Night","current prompt is NULL");
            FindNextValidAction();
        }

        prompts.remove();
    }

    public static void FindNextValidAction(){
        currentActionIndex++;

        while (currentActionIndex < currentPlayer.getRole().getActions().size()){
            currentAction = currentPlayer.getRole().getActions().get(currentActionIndex);

            if(ShouldActionBePrompted(currentAction)){
                QuePrompt(currentAction.getPrompt());
                return;
            }

            currentActionIndex++;
        }

        currentPlayerIndex++;
        if (currentPlayerIndex < allActivePlayers.size()){
            currentActionIndex = -1;
            currentPlayer = allActivePlayers.get(currentPlayerIndex);
            FindNextValidAction();
        }

        OnEndAllPrompts.Invoke();
    }

    private static boolean ShouldActionBePrompted(Action action){
        return true;
    }
}
