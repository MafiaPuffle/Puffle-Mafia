package com.example.pufflemafia.app.game;

import androidx.annotation.NonNull;

import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.Token;

import java.util.Comparator;
import java.util.Vector;
import java.util.function.Predicate;

// Handles all data and logic for a single player
public class Player {

    // Properties
    public String name;

    private Role role;
    public Role getRole() { return role; }
    public void setRole (Role role) {
        this.role.Copy(role);
    }
    public void setRole (String name) {
        this.role.Copy(DataManager.GetRole(name));
    }

    // the tokens applied to this player
    private Vector<Token> tokensOnPlayer;
    public Token getTokenOnPlayer(int index) {
        if(index < tokensOnPlayer.size())
            return tokensOnPlayer.get(index);
        else return null;
    }
    public Vector<Token> getAllTokensOnPlayer() { return tokensOnPlayer; }
    public int getNumberOfTokensOnPlayerOfType(Token.TokenTypes tokenType){
        int numOfTokens = 0;
        for (Token token: tokensOnPlayer) {
            if(token.getType() == tokenType)
                numOfTokens++;
        }
        return numOfTokens;
    }
    public void removeAllTokensOnPlayer(){tokensOnPlayer.clear();}
    public void setTokenAt(int index, Token token){
        tokensOnPlayer.removeElementAt(index);
        tokensOnPlayer.insertElementAt(token, index);
    }

    public void clearAlTokensOfType(Token.TokenTypes typeToCLear){
        Predicate<Token> isTypeToClear = token -> (token.getType() == typeToCLear);

        tokensOnPlayer.removeIf(isTypeToClear);
    }

    // the token this player applies to others
    public Token getToken(){
        return this.role.getPower().getToken();
    }

    // Fills a player with "blank" values
    public Player(){
        this.name = "name";
        this.role = new Role();
        this.tokensOnPlayer = new Vector<Token>();
    }

    // adds the token to tokensOnPlayer
    public void AddTokenOnToPlayer(Token token){
        tokensOnPlayer.add(token);
    }

    public void RemoveTokenAt(int tokenIndex){
        if(tokenIndex >= tokensOnPlayer.size()) return;
        tokensOnPlayer.removeElementAt(tokenIndex);
    }

    public void UpdateTokens(Vector<Token> newTokens){
        tokensOnPlayer.clear();
        tokensOnPlayer = newTokens;
    }

    public void PrintSummary(){
        System.out.print("Player: " + this.name + "\n");
        this.role.PrintSummary("    ");
        System.out.print("  Tokens applied:\n");
        if(this.tokensOnPlayer.size() == 0){
            System.out.print("        NONE\n");
        }
        for (Token token: this.tokensOnPlayer) {
            token.PrintSummary("        ");
        }
    }

    public void PrintSummary(String spacer){
        System.out.print(spacer + "Player: " + this.name + "\n");
        this.role.PrintSummary(spacer + "    ");
        System.out.print(spacer + "  Tokens applied:\n");
        if(this.tokensOnPlayer.size() == 0){
            System.out.print(spacer + "        NONE\n");
        }
        for (Token token: this.tokensOnPlayer) {
            token.PrintSummary(spacer + "        ");
        }
    }

    public void PrintDetailed(){
        System.out.print("Player\n");
        System.out.print("  Name: " + this.name + "\n");
        this.role.PrintDetailed("    ");
        System.out.print("  Tokens applied:\n");
        if(this.tokensOnPlayer.size() == 0){
            System.out.print("        NONE\n");
        }
        for (Token token: this.tokensOnPlayer) {
            token.PrintDetailed("        ");
        }
    }

    public void PrintDetailed(String spacer){
        System.out.print(spacer + "Player\n");
        System.out.print(spacer + "  Name: " + this.name + "\n");
        this.role.PrintDetailed(spacer + "    ");
        System.out.print(spacer + "  Tokens applied:\n");
        if(this.tokensOnPlayer.size() == 0){
            System.out.print(spacer + "        NONE\n");
        }
        for (Token token: this.tokensOnPlayer) {
            token.PrintDetailed(spacer + "        ");
        }
    }

}

// Helper class used to sort players by their role priority
class SortPlayerByPriority implements Comparator<Player> {
    public int compare(@NonNull Player a, @NonNull Player b)
    {
        if(a.getRole().getPriority() < b.getRole().getPriority()) return -1;
        else if (a.getRole().getPriority() == b.getRole().getPriority()) return 0;
        else return 1;
    }
}
