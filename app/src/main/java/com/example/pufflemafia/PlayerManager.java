package com.example.pufflemafia;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.Vector;

// Handles all data and logic for all player in the game
public class PlayerManager {

    public Vector<Player> allAlive;
    public Vector<Player> allDead;

    // values stored and used by the GetNextPlayerForNight() method
    private Vector<Player> playersWithAbilitiesForThisNight;
    private int playerWithAbilityIndex;

    public PlayerManager (){

    }

    // Run whenever we start a new game
    public void NewGame(int numberOfPlayers){
        // resets vectors
        this.allAlive.clear();
        this.allDead.clear();

        // adds players
        for(int i = 0; i < numberOfPlayers; ++i){
            this.AddPlayer(new Player());
        }
    }

    // Run whenever we start a new night
    //      The vector playersWithAbilitesForThisNight is filled
    //      playerWithAbilityIndex is reset
    public void StartNight(){
        playersWithAbilitiesForThisNight.clear();
        playerWithAbilityIndex = -1;  // we will increment it everytime we access it,
                                      // so the first time it will be -1 + 1 = 0

        // filters out the alive players who have no abilities for this night
        for(int i = 0; i < allAlive.size(); ++i){
            Player player = allAlive.get(i);
            Power power = new Power();
            power.Copy(player.getRole().getPower());

            if(power.getType() != Power.PowerType.PASSIVE || power.getType() != Power.PowerType.SELFACTIVE){
                if(GameManager.getNightNumber() > 1 && power.getType() != Power.PowerType.FISTNIGHT){
                    playersWithAbilitiesForThisNight.add(player);
                }
            }
        }

        // Sorts the all players with abilities for this night by priority
        Collections.sort(this.playersWithAbilitiesForThisNight, new SortPlayerByPriority());
    }

    // Returns the next player with an ability for this night
    // If no more players remains it returns null
    public Player GetNextPlayerForNight(){
        playerWithAbilityIndex++;
        if(playerWithAbilityIndex < playersWithAbilitiesForThisNight.size()){
            return playersWithAbilitiesForThisNight.get(playerWithAbilityIndex);
        }
        else return null;
    }

    // Adds a player to the game
    public void AddPlayer(Player player){
        this.allAlive.add(player);
    }

    public void KillPlayer(Player player){
        this.allDead.add(player);
        this.allAlive.remove(player);
    }

    public void RevivePlayer(Player player){
        this.allAlive.add(player);
        this.allDead.remove(player);
    }

    // Adds token from sourcePlayer onto targetPlayer
    public void UseAbilityOnPlayer( @NonNull Player sourcePlayer, @NonNull Player targetPlayer){
        targetPlayer.AddTokenOnToPlayer(sourcePlayer.getToken());
    }
}
