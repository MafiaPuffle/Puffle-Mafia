package com.example.pufflemafia.app.data.actions.conditions;

import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.game.Player;

public class Condition_DoInitiatorsNOTHaveEffect extends Condition{

    private Effect effectToLookFor;
    public void setEffectToLookFor(Effect effectToLookFor) {
        this.effectToLookFor = effectToLookFor;
    }
    public Effect getEffectToLookFor() {
        return effectToLookFor;
    }

    public Condition_DoInitiatorsNOTHaveEffect(Effect effect){
        setEffectToLookFor(effect);
    }

    @Override
    public boolean check(Action action) {
        for (Player initiator: action.getInitiators()) {
            if(initiator.hasEffect(effectToLookFor)) return false;
        }
        return true;
    }
}
