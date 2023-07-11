package com.example.pufflemafia.app.data.effects;

import com.example.pufflemafia.R;
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
    private String linkType;
    public String getLinkType() {
        return linkType;
    }
    private Effect_Linked instance;

    private Vector<Player> allLinkedPlayers;

    public Effect_Linked(String linkType){
        super("Linked", R.drawable.lover_puffle);
        allLinkedPlayers = new Vector<Player>();
        this.linkType = linkType;
        this.instance = this;

        PlayerManager.OnPlayerReceiveEffect.AddListener(new IEvent2Listener<Player, Effect>() {
            @Override
            public void Response(Player player, Effect effect) {
                if(Objects.equals(effect.getName(), name)) {
                    Effect_Linked e = (Effect_Linked) effect;
                    if(e.getLinkType() == instance.getLinkType()){
                        addPlayerToLink(player);
                    }
                }
            }
        });

        PlayerManager.OnActionPrepped.AddListener(new IEventListener<Action>() {
            @Override
            public void Response(Action action) {
                Vector<Player> targets = action.getTargets();
                for (Player target: targets) {
                    if(target.hasEffectWithName(name)){
                        Vector<Effect> effects = target.getEffectsWithName(name);
                        for (Effect effect: effects) {
                            if( ((Effect_Linked) effect).getLinkType() == instance.getLinkType()){
                                addTargetsToAction(action);
                                return;
                            }
                        }
//                        if(e.getLinkType() == instance.getLinkType()){
//                            addTargetsToAction(action);
//                            return;
//                        }
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

    @Override
    public void PrintSummary(){
        System.out.print(name + "(" + linkType + ")" + ", ");
    }
}
