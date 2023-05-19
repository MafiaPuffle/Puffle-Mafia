package com.example.pufflemafia.app.game;

import androidx.annotation.NonNull;

import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.Token;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

// Handles all data and logic for all player in the game
public class PlayerManager {
    public enum PlayerMangerListType {ALIVE, DEAD}


    public static Vector<Player> allAlive;
    public static int numberOfPlayersAlive() {return allAlive.size();}
    public static Vector<Player> allDead;
    public static int numberOfPlayersDead() {return allDead.size();}

    // Used for sending warning messages for debugging
    private static Logger logger;

    public PlayerManager (){
        allAlive = new Vector<Player>();
        allDead = new Vector<Player>();

        logger = Logger.getLogger(PlayerManager.class.getName());
        // Set Logger level()
        logger.setLevel(Level.WARNING);
    }

    // Adds a player to the game
    public static void AddPlayer(Player player){
        allAlive.add(player);
    }

    public static void KillPlayer(Player player){
        allDead.add(player);
        allAlive.remove(player);
    }

    public static void RevivePlayer(Player player){
        allAlive.add(player);
        allDead.remove(player);
    }

    public static void EditPlayerName(PlayerMangerListType listType, int playerIndex, String newName){
        if(listType == PlayerMangerListType.ALIVE){
            if(playerIndex >= allAlive.size()){

                // Call warning method
                logger.warning("attempted to edit a player name outside of all alive");
                return;
            }

            allAlive.elementAt(playerIndex).name = newName;
        }
        else{
            if(playerIndex >= allDead.size()){

                // Call warning method
                logger.warning("attempted to edit a player name outside of all dead");
                return;
            }

            allDead.elementAt(playerIndex).name = newName;
        }
    }

    public static void EditPlayerRole(PlayerMangerListType listType, int playerIndex, Role newRole){
        if(listType == PlayerMangerListType.ALIVE){
            if(playerIndex >= allAlive.size()){

                // Call warning method
                logger.warning("attempted to edit a player role outside of all alive");
                return;
            }

            allAlive.elementAt(playerIndex).setRole(newRole);
        }
        else{
            if(playerIndex >= allDead.size()){

                // Call warning method
                logger.warning("attempted to edit a player role outside of all dead");
                return;
            }

            allDead.elementAt(playerIndex).setRole(newRole);
        }
    }

    public static void EditPlayerToken(PlayerMangerListType listType, int playerIndex, int tokenIndex, Token newToken){
        if(listType == PlayerMangerListType.ALIVE){
            if(playerIndex >= allAlive.size()){

                // Call warning method
                logger.warning("attempted to edit a player token outside of all alive");
                return;
            }
            if(tokenIndex >= allAlive.elementAt(playerIndex).getAllTokensOnPlayer().size()){

                // Call warning method
                logger.warning("attempted to edit a player token outside to their current tokens");
                return;
            }

            allAlive.elementAt(playerIndex).setTokenAt(tokenIndex, newToken);
        }
        else{
            if(playerIndex >= allDead.size()){

                // Call warning method
                logger.warning("attempted to edit a player token outside of all dead");
                return;
            }
            if(tokenIndex >= allDead.elementAt(playerIndex).getAllTokensOnPlayer().size()){

                // Call warning method
                logger.warning("attempted to edit a player token outside to their current tokens");
                return;
            }

            allDead.elementAt(playerIndex).setTokenAt(tokenIndex, newToken);
        }
    }

    // Adds token from sourcePlayer onto targetPlayer
    public static void UseAbilityOnPlayer( @NonNull Role sourceRole, @NonNull Player targetPlayer){
        targetPlayer.AddTokenOnToPlayer(sourceRole.getPower().getToken());
    }


    public static void PrintSummary(){
        System.out.print("\nPlayerManager's State");
        System.out.print("\nAlive:\n");
        for(Player player : allAlive){
            player.PrintSummary("    ");
        }
        System.out.print("\nDead:\n");
        for(Player player : allDead){
            player.PrintSummary("    ");
        }
    }

    public static void PrintDetailed(){
        System.out.print("\nPlayerManager's State");
        System.out.print("\nAlive:\n");
        for(Player player : allAlive){
            player.PrintDetailed("    ");
        }
        System.out.print("\nDead:\n");
        for(Player player : allDead){
            player.PrintDetailed("    ");
        }
    }
}
