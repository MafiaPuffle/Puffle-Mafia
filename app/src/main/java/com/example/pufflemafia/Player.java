package com.example.pufflemafia;

public class Player {
    enum PlayerState {ALIVE, DEAD}

    private String name;
    private Role role;
    public void setRole (String roleName) {
        if(RolesManager.DoesRoleExist(roleName)) role.Copy(RolesManager.getRole(roleName));
    }
    private PlayerState state;
    private String[] tokens;

    public Player(){
        name = "name";
        role = new Role();
        state = PlayerState.ALIVE;
        tokens = new String[25];
    }

    public void AddToken(){
        //TODO: Figure out tokens
    }

}
