package com.example.pufflemafia.app.data.actions.result;

import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

public class Result_KillTargets extends Result{

    private KillType killType;

    public Result_KillTargets(KillType killType){
        this.killType = killType;
    }

    @Override
    public void trigger(Action action){
        for (Player target: action.getTargets()) {
            PlayerManager.killPlayer(target, killType);
            System.out.print(target.getName() + " should have been killed by " + killType + "\n");
        }
    }
}
