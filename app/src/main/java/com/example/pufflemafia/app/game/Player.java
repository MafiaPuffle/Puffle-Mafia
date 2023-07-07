package com.example.pufflemafia.app.game;

import com.example.pufflemafia.app.events.Event;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.data.Role;

import java.util.Vector;

// Handles all data and logic for a single player
public class Player {

    public enum PlayerState {ALIVE, DEAD}

    public Event<PlayerState> OnStateChange;
    private PlayerState currentState;
    public PlayerState getCurrentState() {
        return currentState;
    }
    public void setCurrentState(PlayerState currentState) {
        this.currentState = currentState;
        OnStateChange.Invoke(currentState);
    }

    // Properties
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    private Role role;
    public Event<Role> OnChangeRole;
    public Role getRole() {
        return role;
    }
    public boolean hasRoleWithName(String name){
        if(role.getName() == name){
            return true;
        }
        return false;
    }
    public void changeRole(Role role) {
        OnChangeRole.Invoke(role);
        this.role = role;
        for (Effect effect: effects) {
            if(effect.isStartingEffect())
            {
                effects.remove(effect);
            }
        }
        for (Effect effect: getRole().getStartingEffects()) {
            giveEffect(effect);
        }
    }

    private Vector<Effect> effects;
    public Event<Effect> OnReceiveEffect;
    public Vector<Effect> getEffects(){
        return effects;
    }
    public Effect getEffectWithName(String name){
        for (Effect effect: effects) {
            if(effect.getName() == name){
                return effect;
            }
        }
        return null;
    }
    public Vector<Effect> getEffectsWithName(String name){
        Vector<Effect> output = new Vector<Effect>();

        for (Effect effect: effects) {
            if(effect.getName() == name){
               output.add(effect);
            }
        }

        return output;
    }
    public void giveEffect(Effect effect){
        OnReceiveEffect.Invoke(effect);
        effects.add(effect);
    }
    public boolean hasEffect(Effect effect){
        if(effects.contains(effect)) return true;
        else return false;
    }
    public boolean hasEffectWithName(String name){
        for (Effect effect: effects) {
            if(effect.getName() == name) return true;
        }
        return false;
    }

    public Player(String name, Role role){
        OnStateChange = new Event<PlayerState>();
        OnChangeRole = new Event<Role>();
        OnReceiveEffect = new Event<Effect>();

        setCurrentState(PlayerState.ALIVE);
        setName(name);
        this.effects = new Vector<Effect>();

        changeRole(role);
    }

    public void printSummary(){
        System.out.print(name + " is " + role.getName() + "\n  and has the following effects: ");
        for (Effect effect: effects) {
           effect.PrintSummary();
        }
        System.out.print("\n");
    }
}


