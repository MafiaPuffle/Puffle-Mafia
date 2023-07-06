package com.example.pufflemafia.app.data.actions.result;

import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.game.Player;

import java.util.Vector;

public class Result_CopyRole extends Result{

    public Result_CopyRole(){}

    @Override
    public void trigger(Action action){
        Role roleToCopyTo = action.getTargets().get(0).getRole();
        for (Player initiator: action.getInitiators()) {
            initiator.changeRole(roleToCopyTo);
        }
    }
}
