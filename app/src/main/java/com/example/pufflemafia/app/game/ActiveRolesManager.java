package com.example.pufflemafia.app.game;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.pufflemafia.app.events.Event;
import com.example.pufflemafia.app.data.Power;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.SortByPriority;
import com.example.pufflemafia.app.data.Token;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Vector;

public class ActiveRolesManager {

    public static Event<Boolean> onLookingAtLastRoleForTheNight;

    private static Vector<Role> rolesWithAbilitiesForTheNight;
    private static Vector<Queue<PlayerMemory>> updatedPlayersMemory;
    public static Role StartNight(Vector<Role> allAliveRoles, int nightNumber){

        // Filters out duplicate roles
        Map<String, Role> allUniqueRoles = new HashMap<String, Role>();
        for (Role role: allAliveRoles) {
            allUniqueRoles.put(role.getName(), role);
        }
        allAliveRoles.clear();
        for (Map.Entry<String, Role> entry: allUniqueRoles.entrySet()){
            allAliveRoles.add(entry.getValue());
        }


        rolesWithAbilitiesForTheNight.clear();

        for(int i = 0; i < allAliveRoles.size(); ++i){

            // filters out roles with NULL (-1) priority
            Role role = allAliveRoles.get(i);
            if(role.getPriority() == -1) continue;

            // filters out powers that are PASSIVE, SELFACTIVE,
            // and FIRSTNIGHT if it is not the first night
            Power power = role.getPower();
            if(power.getType() != Power.PowerType.PASSIVE && power.getType() != Power.PowerType.SELFACTIVE){
                if(nightNumber == 1 && power.getType() == Power.PowerType.FIRSTNIGHT){
                    rolesWithAbilitiesForTheNight.add(role);
                }
                else if(power.getType() != Power.PowerType.FIRSTNIGHT){
                    rolesWithAbilitiesForTheNight.add(role);
                }
            }

        }

        String message1 ="";
        for (int i = 0; i < rolesWithAbilitiesForTheNight.size(); i++) {
            message1 += rolesWithAbilitiesForTheNight.get(i).getName() + " ";
        }
        Log.d("ActiveRolesManager", "Order before sort: " + message1);

        Collections.sort(rolesWithAbilitiesForTheNight, new SortByPriority());

        String message2 ="";
        for (int i = 0; i < rolesWithAbilitiesForTheNight.size(); i++) {
            message2 += rolesWithAbilitiesForTheNight.get(i).getName() + " ";
        }
        Log.d("ActiveRolesManager", "Order after sort: " + message2);

        if(rolesWithAbilitiesForTheNight.size() > 0){
            return rolesWithAbilitiesForTheNight.get(0);
        }
        else{
            return null;
        }
    }
    @Nullable
    public static Role GetRoleForNight(int index){
        if(index >= rolesWithAbilitiesForTheNight.size()) {
            onLookingAtLastRoleForTheNight.Invoke(false);
            return null;
        }
        if(index == (rolesWithAbilitiesForTheNight.size() - 1)){
            onLookingAtLastRoleForTheNight.Invoke(true);
        }

        return rolesWithAbilitiesForTheNight.get(index);
    }

    public static void ResetPlayerMemory(){
        updatedPlayersMemory.clear();
    }

    public static void UpdatePlayerMemory(int vectorIndex, Player player, Token token){
        while (vectorIndex >= updatedPlayersMemory.size()){
            Queue emptyQue = new ArrayDeque();
            updatedPlayersMemory.add(emptyQue);
        }

        PlayerMemory playerMemory = new PlayerMemory(player, token);
        updatedPlayersMemory.elementAt(vectorIndex).add(playerMemory);

        if(updatedPlayersMemory.elementAt(vectorIndex).size() > token.getMaxUsableAtNight()){
            Player firstPlayerInQueue = updatedPlayersMemory.elementAt(vectorIndex).peek().getPlayer();
            assert firstPlayerInQueue != null;
            firstPlayerInQueue.RemoveToken(token);
            updatedPlayersMemory.elementAt(vectorIndex).remove();
        }

    }

    public ActiveRolesManager(){
        onLookingAtLastRoleForTheNight = new Event<Boolean>();
        rolesWithAbilitiesForTheNight = new Vector<Role>();
        updatedPlayersMemory = new Vector<Queue<PlayerMemory>>();
    }

    public static void PrintSummary(){
        System.out.print("ActiveRolesManager current state:\n" +
                        " Roles with abilities for this night:\n");
        for(Role role: rolesWithAbilitiesForTheNight){
            role.PrintSummary("  ");
        }
    }

    public static void PrintDetailed(){
        System.out.print("ActiveRolesManager current state:\n" +
                " Roles with abilities for this night:\n");
        for(Role role: rolesWithAbilitiesForTheNight){
            role.PrintDetailed("  ");
        }
    }
}

class PlayerMemory{
    private Player player;
    public Player getPlayer() {
        return player;
    }
    private Token token;
    public Token getToken() {
        return token;
    }

    public PlayerMemory(Player player, Token token){
        this.player = player;
        this.token = token;
    }
}
