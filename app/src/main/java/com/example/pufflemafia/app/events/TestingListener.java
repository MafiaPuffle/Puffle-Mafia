package com.example.pufflemafia.app.events;

import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.ResolvingManager;

public class TestingListener {

    public static void Initialize(){
        PlayerManager.OnPlayerReceiveEffect.AddListener(new IEvent2Listener<Player, Effect>() {
            @Override
            public void Response(Player player, Effect effect) {
                System.out.print(player.getName() + " received the effect ");
                effect.PrintSummary();
                System.out.print("\n");
            }
        });

        PlayerManager.OnPlayerChangeRole.AddListener(new IEvent2Listener<Player, Role>() {
            @Override
            public void Response(Player player, Role role) {
                System.out.print(player.getName() + " changed to the role " + role.getName() + "\n");
            }
        });

        PlayerManager.OnAddPlayer.AddListener(new IEventListener<Player>() {
            @Override
            public void Response(Player player) {
                System.out.print(player.getName() + " was added to the game\n");
            }
        });

        PlayerManager.OnRevivePlayer.AddListener(new IEventListener<Player>() {
            @Override
            public void Response(Player player) {
                System.out.print(player.getName() + " was revived\n");
            }
        });

        PlayerManager.OnKillPlayer.AddListener(new IEvent2Listener<Player, Result.KillType>() {
            @Override
            public void Response(Player player, Result.KillType killType) {
                System.out.print(player.getName() + " was killed by " + killType + "\n");
            }
        });

//        PlayerManager.OnActionPrepped.AddListener(new IEventListener<Action>() {
//            @Override
//            public void Response(Action action) {
//                System.out.print(action.getName() + " was prepped\n");
//                action.PrintSummary();
//            }
//        });

        ResolvingManager.OnActionQue.AddListener(new IEventListener<Action>() {
            @Override
            public void Response(Action action) {
                System.out.print(action.getName() + " was queued\n");
                action.PrintSummary();
            }
        });
    }
}
