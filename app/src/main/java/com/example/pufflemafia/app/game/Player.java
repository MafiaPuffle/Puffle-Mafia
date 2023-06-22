package com.example.pufflemafia.app.game;

import com.example.pufflemafia.app.events.Event;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.data.Role;

import java.util.Vector;

// Handles all data and logic for a single player
public class Player {

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
    public Event<Effect> OnGiveEffect;
    public Vector<Effect> getEffects(){
        return effects;
    }
    public void giveEffect(Effect effect){
        OnGiveEffect.Invoke(effect);
        effects.add(effect);
    }
}


