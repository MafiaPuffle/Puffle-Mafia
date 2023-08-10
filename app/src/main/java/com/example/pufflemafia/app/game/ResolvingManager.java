package com.example.pufflemafia.app.game;

import com.example.pufflemafia.app.events.Event;
import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.events.VoidEvent;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Vector;

public class ResolvingManager {

    public static VoidEvent OnAllNightActionsResolved;
    public static VoidEvent OnAllInstantActionsResolved;

    private static Vector<Action> delayedActions;
    public static void resolveDelayedActions(){
        Vector<Action> resolved = new Vector<Action>();
        for (Action action: delayedActions) {
            action.resolve();
            if(action.HasBeenResolved()){
                resolved.add(action);
                ActionLogManager.addLog(action.getLog());
            }
        }
        delayedActions.removeAll(resolved);
    }

    private static Queue<Action> endOfNightActions;
    public static void resolveEndOfNightActions() {
        while (endOfNightActions.size() > 0){
            Action action = endOfNightActions.peek();
            assert action != null;
            action.resolve();
            ActionLogManager.addLog(action.getLog());
            endOfNightActions.remove();
        }
        OnAllNightActionsResolved.Invoke();
    }
    private static Queue<Action> instantActions;
    public static void resolveEndOfInstantActions() {
        while (instantActions.size() > 0){
            Action action = instantActions.peek();
            assert action != null;
            action.resolve();
            ActionLogManager.addLog(action.getLog());
            instantActions.remove();
        }
        OnAllInstantActionsResolved.Invoke();
    }

    public static Event<Action> OnActionQue;
    public static void queAction(Action action){
        if(action.getWhenTOResolve() == Action.WhenTOResolve.END_OF_NIGHT){
            endOfNightActions.add(action);
            OnActionQue.Invoke(action);
        }
        else if (action.getWhenTOResolve() == Action.WhenTOResolve.INSTANT) {
            instantActions.add(action);
            OnActionQue.Invoke(action);
            resolveEndOfInstantActions();
        }
        else if(action.getWhenTOResolve() == Action.WhenTOResolve.DELAY) {
            delayedActions.add(action);
            OnActionQue.Invoke(action);
        }
    }

    public static void Initialize() {
        endOfNightActions = new ArrayDeque<Action>();
        instantActions = new ArrayDeque<Action>();
        delayedActions = new Vector<Action>();

        OnAllNightActionsResolved = new VoidEvent();
        OnAllInstantActionsResolved = new VoidEvent();
        OnActionQue = new Event<Action>();
    }

    public static void PrintSummary(){
        System.out.print("The Resolving Managers has " + endOfNightActions.size() + " end of night actions left\n" +
                "   and " + instantActions.size() + " instant actions left\n");
    }
}
