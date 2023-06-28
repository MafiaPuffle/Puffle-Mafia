package com.example.pufflemafia.app.game;

import com.example.pufflemafia.app.events.Event;
import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.events.VoidEvent;

import java.util.ArrayDeque;
import java.util.Queue;

public class ResolvingManager {

    public static VoidEvent OnAllNightActionsResolved;
    public static VoidEvent OnAllInstantActionsResolved;
    private static Queue<Action> endOfNightActions;
    public static void resolveEndOfNightActions() {
        while (endOfNightActions.size() > 0){
            Action action = endOfNightActions.peek();
            assert action != null;
            action.resolve();
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
        else{
            System.out.print("RECEIVED ACTION WITH UNKNOWN RESOLVE STATE " + action.getWhenTOResolve() + "\n");
        }
    }

    public static void Initialize() {
        endOfNightActions = new ArrayDeque<Action>();
        instantActions = new ArrayDeque<Action>();

        OnAllNightActionsResolved = new VoidEvent();
        OnAllInstantActionsResolved = new VoidEvent();
        OnActionQue = new Event<Action>();
    }

    public static void PrintSummary(){
        System.out.print("The Resolving Managers has " + endOfNightActions.size() + " end of night actions left\n" +
                "   and " + instantActions.size() + " instant actions left\n");
    }
}
