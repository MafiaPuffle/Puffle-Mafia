package com.example.pufflemafia.app.data.actions.conditions;

import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.game.Player;

public class Condition_DoTargetNOTHaveEffect extends Condition{

    private Effect effectToLookFor;
    public void setEffectToLookFor(Effect effectToLookFor) {
        this.effectToLookFor = effectToLookFor;
    }
    public Effect getEffectToLookFor() {
        return effectToLookFor;
    }

    public Condition_DoTargetNOTHaveEffect(Effect effect){
        super("Do Targets NOT have Effect");
        setEffectToLookFor(effect);
    }

    @Override
    public boolean check(Action action) {
        for (Player target: action.getTargets()) {
            if(target.hasEffect(effectToLookFor)) {
//                System.out.print(target.getName() + " has effect " + effectToLookFor.getName() + "\n");
                return false;
            }
        }
        return true;
    }
}
