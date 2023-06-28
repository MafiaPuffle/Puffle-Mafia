package com.example.pufflemafia.app.data.effects;

import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.events.IEvent2Listener;
import com.example.pufflemafia.app.events.IEventListener;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Vector;

public class Effect_Alert extends Effect {

    private final String name = "Alert";

    public Effect_Alert(){
        super("Alert", true);

        PlayerManager.OnActionPrepped.AddListener(new IEventListener<Action>() {
            @Override
            public void Response(Action action) {
                for (Player target: action.getTargets()) {
                    if(target.hasEffectWithName(name)){
                        Vector<Player> targets = new Vector<Player>();
                        for (Player initiator: action.getInitiators()) {
                            targets.add(initiator);
                        }
                        Action alertKill = DataManager.getAction("alertKill");
                        PlayerManager.prepAction(target, alertKill, targets);
                    }
                }
            }
        });
    }
}
