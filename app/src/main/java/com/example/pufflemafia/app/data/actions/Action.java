package com.example.pufflemafia.app.data.actions;

import com.example.pufflemafia.app.events.Event;
import com.example.pufflemafia.app.data.actions.conditions.Condition;
import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.game.Player;

import java.util.Vector;

public class Action {

    public enum WhenTOResolve {END_OF_NIGHT, INSTANT, DELAY}
    public enum ValidTargets {ALL_PLAYERS, ALL_ALIVE_PLAYERS, ALL_DEAD_PLAYERS, SELF, WITNESS}

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private boolean hasBeenResolved;
    public boolean HasBeenResolved() {
        return hasBeenResolved;
    }
    public void setHasBeenResolved(boolean hasBeenResolved) {
        this.hasBeenResolved = hasBeenResolved;
    }

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


    public Event<Boolean> OnActionResolve;
    public void resolve(){
        for (Condition condition: conditions) {
            if(!condition.check(this)) {
                OnActionResolve.Invoke(false);
                setHasBeenResolved(false);
//                System.out.print(name + " failed to resolve because " + condition.getName() + " returned false\n");
                return;
            }
        }

        for (Result result: results) {
            result.trigger(this);
        }
        OnActionResolve.Invoke(true);
        setHasBeenResolved(true);
//        System.out.print(name + " resolved successfully\n");
    }

    public Action(String name, WhenTOResolve whenTOResolve, ValidTargets validTargets, Vector<Condition> conditions, Vector<Result> results){
        OnActionResolve = new Event<Boolean>();

        setName(name);
        setWhenTOResolve(whenTOResolve);
        setValidTargets(validTargets);
        setConditions(conditions);
        setResults(results);

    }

    public Action(){
        OnActionResolve = new Event<Boolean>();
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
}
