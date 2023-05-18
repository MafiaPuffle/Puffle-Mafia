package com.example.pufflemafia;

import com.example.pufflemafia.data.Role;

public class ListenForWinner implements IListener<Role.Teams>{

    public Role.Teams winningTeam;
    public boolean aTeamWon;

    public ListenForWinner(){
        winningTeam = Role.Teams.TOWN;
        aTeamWon = false;
    }

    @Override
    public void Response() {

    }

    @Override
    public void Response(Role.Teams teams) {
        aTeamWon = true;
        winningTeam = teams;
    }
}
