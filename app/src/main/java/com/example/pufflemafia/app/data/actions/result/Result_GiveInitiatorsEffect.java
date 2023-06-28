package com.example.pufflemafia.app.data.actions.result;

import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.game.Player;

public class Result_GiveInitiatorsEffect extends Result{

    private Effect effect;

    public Result_GiveInitiatorsEffect(Effect effect){
        this.effect = effect;
    }

    @Override
    public void trigger(Action action){
        for (Player target: action.getInitiators()) {
            target.giveEffect(effect);
        }
    }
}
