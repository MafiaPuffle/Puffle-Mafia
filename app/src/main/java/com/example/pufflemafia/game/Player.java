package com.example.pufflemafia.game;

import androidx.annotation.NonNull;

import com.example.pufflemafia.RolesManager;
import com.example.pufflemafia.data.Role;
import com.example.pufflemafia.data.Token;

import java.util.Comparator;
import java.util.Vector;

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
        this.role.Copy(RolesManager.getRoleFromAllRoles(name));
    }

    // the tokens applied to this player
    private Vector<Token> tokensOnPlayer;
    public Token getTokenOnPlayer(int index) {
        if(index < tokensOnPlayer.size())
            return tokensOnPlayer.get(index);
        else return null;
    }
    public Vector<Token> getAllTokensOnPlayer() { return tokensOnPlayer; }
    public void setTokenAt(int index, Token token){
        tokensOnPlayer.removeElementAt(index);
        tokensOnPlayer.insertElementAt(token, index);
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
