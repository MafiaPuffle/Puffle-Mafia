package com.example.pufflemafia.game;

import androidx.annotation.Nullable;

import com.example.pufflemafia.Event;
import com.example.pufflemafia.data.DataManager;
import com.example.pufflemafia.data.Power;
import com.example.pufflemafia.data.Role;
import com.example.pufflemafia.data.SortByPriority;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Vector;

public class ActiveRolesManager {

    public static Event<Boolean> onLookingAtLastRoleForTheNight;

    private static Vector<Role> rolesWithAbilitiesForTheNight;
    public static void StartNight(Vector<Role> allAliveRoles, int nightNumber){

        // Filters out duplicate roles
        LinkedHashSet<Role> allUniqueRoles = new LinkedHashSet<Role>(allAliveRoles);
        allAliveRoles.clear();
        allAliveRoles.addAll(allUniqueRoles);


        rolesWithAbilitiesForTheNight.clear();

        for(int i = 0; i < allAliveRoles.size(); ++i){

            // filters out roles with NULL (-1) priority
            Role role = allAliveRoles.get(i);
            if(role.getPriority() == -1) continue;

            // filters out powers that are PASSIVE, SELFACTIVE,
            // and FIRSTNIGHT if it is not the first night
            Power power = role.getPower();
            if(power.getType() != Power.PowerType.PASSIVE && power.getType() != Power.PowerType.SELFACTIVE){
                if(nightNumber > 1 && power.getType() != Power.PowerType.FIRSTNIGHT){
                    rolesWithAbilitiesForTheNight.add(role);
                }
                else{
                    rolesWithAbilitiesForTheNight.add(role);
                }
            }

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
        //TODO: figure out ActiveRolesManager
        rolesWithAbilitiesForTheNight = new Vector<Role>();
    }
}
