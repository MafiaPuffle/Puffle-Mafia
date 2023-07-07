package com.example.pufflemafia.app.game.states;

import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.events.IEventListener;
import com.example.pufflemafia.app.events.VoidEvent;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Collections;
import java.util.Vector;

public class Setup {

    public static VoidEvent OnAddName;
    public static VoidEvent OnRemoveName;
    public static Vector<String> names;
    public static Vector<String> getNames() {
        return names;
    }
    public static void addName(String name){
        names.add(name);
        OnAddName.Invoke();
    }
    public static void removeName(String name){
        names.remove(name);
        OnRemoveName.Invoke();
    }

    public static VoidEvent OnAddRole;
    public static VoidEvent OnRemoveRole;
    public static Vector<Role> roles;
    public static Vector<Role> getRoles() {
        return roles;
    }
    public static void addRole(Role role){
        roles.add(role);
        OnAddRole.Invoke();
    }
    public static void removeRole(Role role){
        roles.remove(role);
        OnRemoveRole.Invoke();
    }
    public static void removeAllRoles(){
        roles.clear();
        OnRemoveRole.Invoke();
    }

    public static void Initialize(){
        names = new Vector<String>();
        roles = new Vector<Role>();

        OnAddName = new VoidEvent();
        OnRemoveName = new VoidEvent();
        OnAddRole = new VoidEvent();
        OnRemoveRole = new VoidEvent();

        GameManager.OnGameStateChange.AddListener(new IEventListener<GameManager.gameState>() {
            @Override
            public void Response(GameManager.gameState gameState) {
                if(gameState == GameManager.gameState.SETUP){
                    Start();
                }
            }
        });
    }

    public static void Start(){

    }

    public static void SetUpGame(){
        Collections.shuffle(names);
        Collections.shuffle(roles);

        if(areThereAsManyRolesAsNames()){
            for (int i = 0; i < names.size(); i++) {
                PlayerManager.addPlayerToGame(new Player(names.get(i), roles.get(i)));
            }
        }
        else if (areThereMoreRolesThanNames()) {
            for (int i = 0; i < roles.size(); i++) {
                if(i < names.size()){
                    PlayerManager.addPlayerToGame(new Player(names.get(i), roles.get(i)));
                }
                else {
                    PlayerManager.addPlayerToGame(new Player("Player" + (i + 1), roles.get(i)));
                }
            }
        }
        else if (areThereMoreNamesThanRoles()) {
            for (int i = 0; i < names.size(); i++) {
                if(i < roles.size()){
                    PlayerManager.addPlayerToGame(new Player(names.get(i), roles.get(i)));
                }
                else {
                    PlayerManager.addPlayerToGame(new Player(names.get(i), DataManager.getRandomRole()));
                }
            }
        }

        GameManager.setCurrentGameState(GameManager.gameState.DAY);
    }

    private static boolean areThereAsManyRolesAsNames(){
        if(roles.size() == names.size()) return true;
        else return false;
    }
    private static boolean areThereMoreNamesThanRoles(){
        if(names.size() > roles.size()) return true;
        else return false;
    }
    private static boolean areThereMoreRolesThanNames(){
        if(names.size() < roles.size()) return true;
        else return false;
    }
}
