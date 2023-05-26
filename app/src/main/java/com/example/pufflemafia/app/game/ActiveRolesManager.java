package com.example.pufflemafia.app.game;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.pufflemafia.app.Event;
import com.example.pufflemafia.app.data.Power;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.SortByPriority;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Vector;

public class ActiveRolesManager {

    public static Event<Boolean> onLookingAtLastRoleForTheNight;

    private static Vector<Role> rolesWithAbilitiesForTheNight;
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
        if(index >= rolesWithAbilitiesForTheNight.size()) return null;
        if(index == (rolesWithAbilitiesForTheNight.size() - 1)){
            onLookingAtLastRoleForTheNight.Invoke(true);
        }

        return rolesWithAbilitiesForTheNight.get(index);
    }


    public ActiveRolesManager(){
        onLookingAtLastRoleForTheNight = new Event<Boolean>();
        rolesWithAbilitiesForTheNight = new Vector<Role>();
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
