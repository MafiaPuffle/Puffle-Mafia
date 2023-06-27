package com.example.pufflemafia.app.data.effects;

import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.events.IEvent2Listener;
import com.example.pufflemafia.app.events.IEventListener;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Objects;
import java.util.Vector;

public class Effect_Linked extends Effect{

    private final String name = "Linked";

    private Vector<Player> allLinkedPlayers;

    public Effect_Linked(){
        super("Linked");
        allLinkedPlayers = new Vector<Player>();

        PlayerManager.OnPlayerReceiveEffect.AddListener(new IEvent2Listener<Player, Effect>() {
            @Override
            public void Response(Player player, Effect effect) {
                System.out.print(player.getName() + " received the effect " + effect.getName() + "\n");
                if(Objects.equals(effect.getName(), name)) addPlayerToLink(player);
            }
        });

//        PlayerManager.OnKillPlayer.AddListener(new IEvent2Listener<Player, Result.KillType>() {
//            @Override
//            public void Response(Player player, Result.KillType killType) {
//                if(playerIsLinked(player)){
//                    for (Player p: allLinkedPlayers) {
//                        if(p != player) PlayerManager.killPlayer_NOEVENT(p,killType);
//                    }
//                }
//            }
//        });

        PlayerManager.OnActionPrepped.AddListener(new IEventListener<Action>() {
            @Override
            public void Response(Action action) {
                Vector<Player> targets = action.getTargets();
                for (Player target: targets) {
                    if(target.hasEffectWithName(name)){
                        addTargetsToAction(action);
                        return;
                    }
                }
            }
        });
    }

    private void addTargetsToAction(Action action){
        for (Player target: allLinkedPlayers) {
            action.addUniqueTarget(target);
        }
    }

    private void addPlayerToLink(Player player){
        allLinkedPlayers.add(player);
    }

    private boolean playerIsLinked(Player player){
        if(allLinkedPlayers.contains(player)) return true;
        else return false;
    }
}
