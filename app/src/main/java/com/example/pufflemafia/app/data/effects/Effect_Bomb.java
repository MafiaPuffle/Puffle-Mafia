package com.example.pufflemafia.app.data.effects;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.events.IEvent2Listener;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Objects;
import java.util.Vector;

public class Effect_Bomb extends Effect {

    private final String name = "Bomb";

    private Vector<Player> playersWithBombsOnThem;

    public Effect_Bomb(){
        super("Bomb", R.drawable.terrorist_puffle);
        playersWithBombsOnThem = new Vector<Player>();

        PlayerManager.OnPlayerReceiveEffect.AddListener(new IEvent2Listener<Player, Effect>() {
            @Override
            public void Response(Player player, Effect effect) {
                if(Objects.equals(effect.getName(), name)){
                    playersWithBombsOnThem.add(player);
                }
            }
        });

        PlayerManager.OnKillPlayer.AddListener(new IEvent2Listener<Player, Result.KillType>() {
            @Override
            public void Response(Player player, Result.KillType killType) {
                if(player.hasRoleWithName("Terrorist")){
                    for (Player p: playersWithBombsOnThem) {
                        PlayerManager.killPlayer(p, Result.KillType.BOMB);
                    }
                }
            }
        });
    }
}
