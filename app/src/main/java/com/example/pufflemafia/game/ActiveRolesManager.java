package com.example.pufflemafia.game;

import com.example.pufflemafia.data.DataManager;
import com.example.pufflemafia.data.Role;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ActiveRolesManager {
    private static Map<String, Role> allRolesActiveInThisGame;
    public static Role getActiveRole(String name){ return allRolesActiveInThisGame.get(name);}
    public static void addActiveRole(String name){
        if(allRolesActiveInThisGame.containsKey(name)) return;

        Role role = DataManager.GetRole(name);
        allRolesActiveInThisGame.put(name, role);
    }
    public static void removeActiveRole(String name){
        if(!allRolesActiveInThisGame.containsKey(name)) return;

        allRolesActiveInThisGame.remove(name);
    }

    private static Vector<Role> rolesAlive;
    private static int indexOfRolesAlive;
    public static void StartNight(){
        indexOfRolesAlive = 0;
        //Collections.sort(rolesAl)
    }

    public ActiveRolesManager(){
        allRolesActiveInThisGame = new HashMap<String, Role>();
        rolesAlive = new Vector<Role>();
    }
}
