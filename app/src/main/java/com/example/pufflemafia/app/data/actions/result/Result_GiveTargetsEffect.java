package com.example.pufflemafia.app.data.actions.result;

import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

public class Result_GiveTargetsEffect extends Result{

    private Effect effect;

    public Result_GiveTargetsEffect(Effect effect){
        this.effect = effect;
    }

    @Override
    public void trigger(Action action){
        for (Player target: action.getTargets()) {
            target.giveEffect(effect);
        }
    }
}
