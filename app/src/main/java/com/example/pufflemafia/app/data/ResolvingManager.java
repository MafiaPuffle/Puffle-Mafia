package com.example.pufflemafia.app.data;

import com.example.pufflemafia.app.events.Event;
import com.example.pufflemafia.app.data.actions.Action;

import java.util.ArrayDeque;
import java.util.Queue;

public class ResolvingManager {

    private static Queue<Action> endOfNightActions;
    public static void resolveEndOfNightActions() {
        while (endOfNightActions.size() > 0){
            Action action = endOfNightActions.peek();
            assert action != null;
            action.resolve();
            endOfNightActions.remove();
        }
    }
    private static Queue<Action> instantActions;
    public static void resolveEndOfInstantActions() {
        while (instantActions.size() > 0){
            Action action = instantActions.peek();
            assert action != null;
            action.resolve();
            instantActions.remove();
        }
    }

    public static void Initialize() {
        endOfNightActions = new ArrayDeque<Action>();
        instantActions = new ArrayDeque<Action>();
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
    }
}
