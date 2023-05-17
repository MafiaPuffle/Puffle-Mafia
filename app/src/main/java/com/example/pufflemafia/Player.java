package com.example.pufflemafia;

import androidx.annotation.NonNull;

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
    private Vector<Integer> tokensOnPlayer;
    public Integer getTokneOnPlayerAt(int index) {
        if(index < tokensOnPlayer.size())
            return tokensOnPlayer.get(index);
        else return null;
    }
    public Vector<Integer> getAllTokensOnPlayer() { return tokensOnPlayer; }

    // the token this player applies to others
    public int getToken(){
        return this.role.getImageResource();
    }

    // Fills a player with "blank" values
    public Player(){
        this.name = "name";
        this.role = new Role();
        this.tokensOnPlayer = new Vector<Integer>();
    }

    // adds the token to tokensOnPlayer
    public void AddTokenOnToPlayer(int token){
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
