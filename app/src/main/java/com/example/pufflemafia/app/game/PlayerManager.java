package com.example.pufflemafia.app.game;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.pufflemafia.app.Event;
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

    public static Event<Boolean> onPlayerDataUpdated;
    public static Event<Boolean> onPlayerKillOrRevive;

    // Used for sending warning messages for debugging
    private static Logger logger;

    public PlayerManager (){
        allAlive = new Vector<Player>();
        allDead = new Vector<Player>();

        logger = Logger.getLogger(PlayerManager.class.getName());
        // Set Logger level()
        logger.setLevel(Level.WARNING);

        onPlayerKillOrRevive = new Event<Boolean>();
        onPlayerDataUpdated = new Event<Boolean>();
    }

    // Adds a player to the game
    public static void AddPlayer(Player player){
        allAlive.add(player);
    }

    public static void KillPlayer(@NonNull Player player){
        player.clearAlTokensOfType(Token.TokenTypes.CLEAR_ON_DEATH);
        allDead.add(player);
        allAlive.remove(player);
        onPlayerKillOrRevive.Invoke(true);
    }

    public static void RevivePlayer(Player player){
        allAlive.add(player);
        allDead.remove(player);
        onPlayerKillOrRevive.Invoke(false);
    }

    public static void ClearAllNightTokens(){
        for (Player player: allAlive) {
            player.clearAlTokensOfType(Token.TokenTypes.CLEAR_ON_NIGHT);
        }

        for (Player player: allDead) {
            player.clearAlTokensOfType(Token.TokenTypes.CLEAR_ON_NIGHT);
        }
    }

    public static void EditPlayerName(PlayerMangerListType listType, int playerIndex, String newName){
        if(listType == PlayerMangerListType.ALIVE){
            if(playerIndex >= allAlive.size()){

                // Call warning method
                logger.warning("attempted to edit a player name outside of all alive");
                return;
            }

            allAlive.elementAt(playerIndex).name = newName;
            onPlayerDataUpdated.Invoke();
        }
        else{
            if(playerIndex >= allDead.size()){

                // Call warning method
                logger.warning("attempted to edit a player name outside of all dead");
                return;
            }

            allDead.elementAt(playerIndex).name = newName;
            onPlayerDataUpdated.Invoke();
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
            onPlayerDataUpdated.Invoke();
        }
        else{
            if(playerIndex >= allDead.size()){

                // Call warning method
                logger.warning("attempted to edit a player role outside of all dead");
                return;
            }

            allDead.elementAt(playerIndex).setRole(newRole);
            onPlayerDataUpdated.Invoke();
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
            onPlayerDataUpdated.Invoke();
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
            onPlayerDataUpdated.Invoke();
        }
    }

    public static void AddTokenToPlayer(PlayerMangerListType listType, int playerIndex, Token newToken){
        if(listType == PlayerMangerListType.ALIVE){
            if(playerIndex >= allAlive.size()){

                // Call warning method
                logger.warning("attempted to edit a player token outside of all alive");
                return;
            }

            allAlive.elementAt(playerIndex).AddTokenOnToPlayer(newToken);
            onPlayerDataUpdated.Invoke();
        }
        else{
            if(playerIndex >= allDead.size()){

                // Call warning method
                logger.warning("attempted to edit a player token outside of all dead");
                return;
            }

            allDead.elementAt(playerIndex).AddTokenOnToPlayer(newToken);
            onPlayerDataUpdated.Invoke();
        }
    }

    public static void UpdateAllTokensOnSinglePlayer(PlayerMangerListType listType, int playerIndex, Vector<Token> updatedTokens){
        if(listType == PlayerMangerListType.ALIVE){
            if(playerIndex >= allAlive.size()){

                // Call warning method
                logger.warning("attempted to remove a player token outside of all alive");
                return;
            }

            allAlive.elementAt(playerIndex).UpdateTokens(updatedTokens);
            onPlayerDataUpdated.Invoke();
        }
        else{
            if(playerIndex >= allDead.size()){

                // Call warning method
                logger.warning("attempted to remove a player token outside of all dead");
                return;
            }

            allDead.elementAt(playerIndex).UpdateTokens(updatedTokens);
            onPlayerDataUpdated.Invoke();
        }
    }

    public static void RemovePlayerToken(PlayerMangerListType listType, int playerIndex, int tokenIndex){
        if(listType == PlayerMangerListType.ALIVE){
            if(playerIndex >= allAlive.size()){

                // Call warning method
                logger.warning("attempted to remove a player token outside of all alive");
                return;
            }
            if(tokenIndex >= allAlive.elementAt(playerIndex).getAllTokensOnPlayer().size()){

                // Call warning method
                logger.warning("attempted to remove a player token outside to their current tokens");
                return;
            }

            allAlive.elementAt(playerIndex).RemoveTokenAt(tokenIndex);
            onPlayerDataUpdated.Invoke();
        }
        else{
            if(playerIndex >= allDead.size()){

                // Call warning method
                logger.warning("attempted to remove a player token outside of all dead");
                return;
            }
            if(tokenIndex >= allDead.elementAt(playerIndex).getAllTokensOnPlayer().size()){

                // Call warning method
                logger.warning("attempted to remove a player token outside to their current tokens");
                return;
            }

            allDead.elementAt(playerIndex).RemoveTokenAt(tokenIndex);
            onPlayerDataUpdated.Invoke();
        }
    }

    public static void RemovePlayerAllToken(PlayerMangerListType listType, int playerIndex){
        if(listType == PlayerMangerListType.ALIVE){
            if(playerIndex >= allAlive.size()){

                // Call warning method
                logger.warning("attempted to remove a player token outside of all alive");
                return;
            }

            allAlive.elementAt(playerIndex).removeAllTokensOnPlayer();
            onPlayerDataUpdated.Invoke();
        }
        else{
            if(playerIndex >= allDead.size()){

                // Call warning method
                logger.warning("attempted to remove a player token outside of all dead");
                return;
            }


            allDead.elementAt(playerIndex).removeAllTokensOnPlayer();
            onPlayerDataUpdated.Invoke();
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

    public static void LogSummary(){
        String output = "";

        output += "\nPlayerManager's State";
        output += "\nAlive:\n";
        for(Player player: allAlive){
            output += "  " + player.name + "\n";
            output += "    Role: " + player.getRole().getName() + "\n";
            for(Token token: player.getAllTokensOnPlayer()){
                output += "    " + token.getName() + "\n";
            }
        }
        output += "\nDead:\n";
        for(Player player: allDead){
            output += "  " + player.name + "\n";
            for(Token token: player.getAllTokensOnPlayer()){
                output += "    " + token.getName() + "\n";
            }
        }

        Log.d("PlayerManager",output);
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
