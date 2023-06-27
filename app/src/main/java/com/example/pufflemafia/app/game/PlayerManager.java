package com.example.pufflemafia.app.game;

import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.events.Event;
import com.example.pufflemafia.app.events.Event2;
import com.example.pufflemafia.app.events.IEventListener;

import java.util.Vector;

// Handles all data and logic for all player in the game
public class PlayerManager {

    public static Event2<Player, Effect> OnPlayerReceiveEffect;
    public static Event<Player> OnAddPlayer;
    public static Event<Player> OnRevivePlayer;
    public static Event<Action> OnActionPrepped;
    private static Vector<Player> allAlivePlayers;
    public static Vector<Player> getAllAlivePlayers() {
        return allAlivePlayers;
    }
    public static void setAllAlivePlayers(Vector<Player> _allAlivePlayers) {
        allAlivePlayers = _allAlivePlayers;
    }
    public static void addPlayerToGame(Player player){
        allAlivePlayers.add(player);

        player.OnReceiveEffect.AddListener(new IEventListener<Effect>() {
            @Override
            public void Response(Effect effect) {
                OnPlayerReceiveEffect.Invoke(player, effect);
            }
        });

        for (Effect effect: player.getEffects()) {
            player.OnReceiveEffect.Invoke(effect);
        }

        OnAddPlayer.Invoke(player);
    }
    public static void revivePlayer(Player player){
        allAlivePlayers.add(player);
        allDeadPlayers.remove(player);
        OnRevivePlayer.Invoke(player);
    }

    public static Event2<Player, Result.KillType> OnKillPlayer;
    private static Vector<Player> allDeadPlayers;
    public static Vector<Player> getAllDeadPlayers() {
        return allDeadPlayers;
    }
    public static void setAllDeadPlayers(Vector<Player> _allDeadPlayers) {
        allDeadPlayers = _allDeadPlayers;
    }
    public static void killPlayer(Player player, Result.KillType killType){
        allAlivePlayers.remove(player);
        allDeadPlayers.add(player);
        OnKillPlayer.Invoke(player,killType);
    }
    public static void killPlayer_NOEVENT(Player player, Result.KillType killType){
        allAlivePlayers.remove(player);
        allDeadPlayers.add(player);
    }

    public static void prepAction(Player player, Action action, Vector<Player> chosenTargets){
        if(player.getRole().hasAction(action)){
            action.setTargets(chosenTargets);

            Vector<Player> initiators = new Vector<Player>();
            for (Player p: allAlivePlayers) {
                if(p.getRole() == player.getRole()){
                    initiators.add(p);
                }
            }

            action.setInitiators(initiators);

            OnActionPrepped.Invoke(action);

            ResolvingManager.queAction(action);
        }
    }

    public static void Initialize(){
        allAlivePlayers = new Vector<Player>();
        allDeadPlayers = new Vector<Player>();

        OnPlayerReceiveEffect = new Event2<Player,Effect>();
        OnAddPlayer = new Event<Player>();
        OnRevivePlayer = new Event<Player>();
        OnKillPlayer = new Event2<Player, Result.KillType>();
        OnActionPrepped = new Event<Action>();
    }

    public static void PrintSummary(){
        System.out.print("Alive\n");
        for (Player player : allAlivePlayers) {
            player.printSummary();
        }

        System.out.print("Dead\n");
        for (Player player : allDeadPlayers) {
            player.printSummary();
        }
    }
}
