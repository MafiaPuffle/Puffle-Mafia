package com.example.pufflemafia;

import java.util.Vector;

public class PlayerManager {

    private Vector<Player> allAlive;
    private Vector<Player> allDead;

    public PlayerManager (){

    }

    public void AddPlayer(Player player){
        allAlive.add(player);
    }

    public void KillPlayer(Player player){
        allDead.add(player);
        allAlive.remove(player);
    }

    public void RevivePlayer(Player player){
        allAlive.add(player);
        allDead.remove(player);
    }

    public void UseAbilityOnPlayer(Player sourcePlayer, Player targetPlayer){
        targetPlayer.AddToken();
    }
}
