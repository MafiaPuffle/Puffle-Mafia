package com.example.pufflemafia.app.game;

import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.events.Event;
import com.example.pufflemafia.app.events.Event2;

import java.util.Vector;

// Handles all data and logic for all player in the game
public class PlayerManager {

    public static Event<Player> OnAddPlayer;
    public static Event<Player> OnRevivePlayer;
    private static Vector<Player> allAlivePlayers;
    public static Vector<Player> getAllAlivePlayers() {
        return allAlivePlayers;
    }
    public static void setAllAlivePlayers(Vector<Player> _allAlivePlayers) {
        allAlivePlayers = _allAlivePlayers;
    }
    public static void addPlayerToGame(Player player){
        allAlivePlayers.add(player);
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
        allDeadPlayers.remove(player);
        OnKillPlayer.Invoke(player,killType);
    }

    public static void Initialize(){
        allAlivePlayers = new Vector<Player>();
        allDeadPlayers = new Vector<Player>();

        OnAddPlayer = new Event<Player>();
        OnRevivePlayer = new Event<Player>();
        OnKillPlayer = new Event2<Player, Result.KillType>();
    }
}
