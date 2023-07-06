package com.example.pufflemafia.app.data.effects;

import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.events.IEvent2Listener;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Objects;
import java.util.Vector;

public class Effect_Baker extends Effect {

    private final String name = "Baker";

    public Effect_Baker(){
        super("Baker", true);

        PlayerManager.OnKillPlayer.AddListener(new IEvent2Listener<Player, Result.KillType>() {
            @Override
            public void Response(Player player, Result.KillType killType) {
                if(player.hasEffectWithName(name)){
                    for (Player p: PlayerManager.getAllAlivePlayers()) {
                        Vector<Player> target = new Vector<Player>();
                        target.add(p);
                        PlayerManager.prepAction(p, DataManager.getAction("famine"), target);
                    }
                }
            }
        });
    }
}
