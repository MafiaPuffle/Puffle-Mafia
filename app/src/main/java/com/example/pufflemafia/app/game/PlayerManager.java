package com.example.pufflemafia.app.game;

import com.example.pufflemafia.app.events.Event;

import java.util.Vector;

// Handles all data and logic for all player in the game
public class PlayerManager {

    public Event OnAddPlayer;
    public Event OnRevivePlayer;
    private Vector<Player> allAlivePlayers;
    public Vector<Player> getAllAlivePlayers() {
        return allAlivePlayers;
    }
    public void setAllAlivePlayers(Vector<Player> allAlivePlayers) {
        this.allAlivePlayers = allAlivePlayers;
    }
    public void addPlayerToGame(Player player){
        allAlivePlayers.add(player);
        OnAddPlayer.Invoke(player);
    }
    public void revivePlayer(Player player){
        allAlivePlayers.add(player);
        allDeadPlayers.remove(player);
        OnRevivePlayer.Invoke(player);
    }

    public Event OnKillPlayer;
    private Vector<Player> allDeadPlayers;
    public Vector<Player> getAllDeadPlayers() {
        return allDeadPlayers;
    }
    public void setAllDeadPlayers(Vector<Player> allDeadPlayers) {
        this.allDeadPlayers = allDeadPlayers;
    }
    public void killPlayer(Player player){

    }
}
