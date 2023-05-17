package com.example.pufflemafia;

import java.util.Comparator;

// Handles all data and logic for a single player
public class Player {

    private String name;
    private Role role;
    public Role getRole() { return role; }
    public void setRole (Role role) {
        this.role.Copy(role);
    }
    public void setRole (String name) {
        this.role.Copy(RolesManager.getRoleFromAllRoles(name));
    }
    private String[] tokens;

    // Fills a player with "blank" values
    public Player(){
        name = "name";
        role = new Role();
        tokens = new String[25];
    }

    public void AddToken(){
        //TODO: Figure out tokens
    }

}

// Helper class used to sort players by their role priority
class SortPlayerByPriority implements Comparator<Player> {
    public int compare(Player a, Player b)
    {
        if(a.getRole().getPriority() < b.getRole().getPriority()) return -1;
        else if (a.getRole().getPriority() == b.getRole().getPriority()) return 0;
        else return 1;
    }
}
