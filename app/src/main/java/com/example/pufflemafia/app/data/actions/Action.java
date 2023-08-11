package com.example.pufflemafia.app.data.actions;

import android.util.Log;

import com.example.pufflemafia.app.data.prompts.Prompt;
import com.example.pufflemafia.app.events.Event;
import com.example.pufflemafia.app.data.actions.conditions.Condition;
import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.states.Night;

import java.util.Vector;

public class Action {

    public enum ActionType {EVERY_NIGHT, ONE_TIME_USE}
    public enum WhenTOResolve {END_OF_NIGHT, INSTANT, DELAY}
    public enum ValidTargets {ALL_PLAYERS, ALL_ALIVE_PLAYERS, ALL_DEAD_PLAYERS, SELF, WITNESS}

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private ActionType actionType;
    public ActionType getActionType() {
        return actionType;
    }
    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    private Prompt prompt;
    public Prompt getPrompt() {
        return prompt;
    }
    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    private boolean hasBeenResolved;
    public boolean HasBeenResolved() {
        return hasBeenResolved;
    }
    public void setHasBeenResolved(boolean hasBeenResolved) {
        this.hasBeenResolved = hasBeenResolved;
    }

    public boolean hasBeenUsedOnce;

    private WhenTOResolve whenTOResolve;
    public void setWhenTOResolve(WhenTOResolve whenTOResolve) {
        this.whenTOResolve = whenTOResolve;
    }
    public WhenTOResolve getWhenTOResolve() {
        return whenTOResolve;
    }

    private ValidTargets validTargets;
    public ValidTargets getValidTargets() {
        return validTargets;
    }
    public void setValidTargets(ValidTargets validTargets) {
        this.validTargets = validTargets;
    }

    private Vector<Player> targets;
    public Vector<Player> getTargets() {
        return targets;
    }
    public void setTargets(Vector<Player> targets) {
        this.targets = targets;
    }
    public void addUniqueTarget(Player newTarget) {
        if(this.targets.contains(newTarget)) return;

        this.targets.add(newTarget);
    }

    private Vector<Player> initiators;
    public Vector<Player> getInitiators() {
        return initiators;
    }
    public void setInitiators(Vector<Player> initiators) {
        this.initiators = initiators;
    }

    private Vector<Condition> conditions;
    public Vector<Condition> getConditions() {
        return conditions;
    }
    public void setConditions(Vector<Condition> conditions) {
        this.conditions = conditions;
    }

    private Vector<Result> results;
    public Vector<Result> getResults() {
        return results;
    }
    public void setResults(Vector<Result> results) {
        this.results = results;
    }

    private ActionLog log;
    public ActionLog getLog(){
        return log;
    }

    private void writeLog(){

        if(initiators.size() == 1){
            log.addToMessage(initiators.get(0).getName());
        } else if (initiators.size() == 2) {
            log.addToMessage(initiators.get(0).getName() + " & " + initiators.get(1).getName());
        } else {
            for (int i = 0; i < initiators.size(); i++) {
                int j = i++;

                if(j == initiators.size()){
                    log.addToMessage(" & " + initiators.get(i).getName());
                }else {
                    log.addToMessage(initiators.get(i).getName() + ", ");
                }
            }
        }

        if(hasBeenResolved){
            log.addToMessage(" used ");
        } else {
            log.addToMessage(" failed to use ");
        }

        log.addToMessage(name + " on ");

        if(targets.size() == 1){
            log.addToMessage(targets.get(0).getName());
        } else if (targets.size() == 2) {
            log.addToMessage(targets.get(0).getName() + " & " + targets.get(1).getName());
        } else {
            for (int i = 0; i < targets.size(); i++) {
                int j = i++;

                if(j == targets.size()){
                    log.addToMessage(" & " + targets.get(i).getName());
                }else {
                    log.addToMessage(targets.get(i).getName() + ", ");
                }
            }
        }

        log.addTag( "Night " + Night.getNightNumber());
    }

    public Event<Boolean> OnActionResolve;
    public void resolve(){
        log = new ActionLog();

        for (Condition condition: conditions) {
            if(!condition.check(this)) {
                OnActionResolve.Invoke(false);
                setHasBeenResolved(false);
                writeLog();
//                System.out.print(name + " failed to resolve because " + condition.getName() + " returned false\n");
                hasBeenUsedOnce = true;
                return;
            }
        }

        for (Result result: results) {
            result.trigger(this);
        }
        OnActionResolve.Invoke(true);
        hasBeenUsedOnce = true;
        setHasBeenResolved(true);
        writeLog();
//        System.out.print(name + " resolved successfully\n");
    }

    public Action(String name, ActionType actionType, WhenTOResolve whenTOResolve, ValidTargets validTargets, Vector<Condition> conditions, Vector<Result> results){
        OnActionResolve = new Event<Boolean>();
        hasBeenUsedOnce = false;

        setActionType(actionType);
        setName(name);
        setWhenTOResolve(whenTOResolve);
        setValidTargets(validTargets);
        setConditions(conditions);
        setResults(results);

    }

    public Action(){
        OnActionResolve = new Event<Boolean>();
        hasBeenUsedOnce = false;

        setActionType(ActionType.EVERY_NIGHT);
    }

    public void PrintSummary(){
        System.out.print(name + " initiated by ");
        for (Player p: initiators) {
            System.out.print(p.getName() + " ");
        }
        System.out.print("with targets ");
        for (Player p: targets) {
            System.out.print(p.getName() + " ");
        }
        System.out.print("\n");
    }

    public void LogSummary(){
        Log.d("CustomTestingListener",name + " initiated by ");
        for (Player p: initiators) {
            Log.d("CustomTestingListener",p.getName() + " ");
        }
        Log.d("CustomTestingListener","with targets ");
        for (Player p: targets) {
            Log.d("CustomTestingListener",p.getName() + " ");
        }
    }
}
