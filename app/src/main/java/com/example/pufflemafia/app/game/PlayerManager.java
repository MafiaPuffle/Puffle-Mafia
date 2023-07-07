package com.example.pufflemafia.app.game;

import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.events.Event;
import com.example.pufflemafia.app.events.Event2;
import com.example.pufflemafia.app.events.IEventListener;
import com.example.pufflemafia.app.events.VoidEvent;

import java.util.Objects;
import java.util.UUID;
import java.util.Vector;

// Handles all data and logic for all player in the game
public class PlayerManager {

    public enum PlayerManagerListType {ALIVE, DEAD}

    public static Event2<Player, Effect> OnPlayerReceiveEffect;
    public static Event2<Player, Role> OnPlayerChangeRole;
    public static Event<Player> OnAddPlayer;
    public static Event<Player> OnRemovePlayer;
    public static Event<Player> OnRevivePlayer;
    public static Event<Action> OnActionPrepped;
    private static Vector<Player> allAlivePlayers;
    public static Vector<Player> getAllAlivePlayers() {
        return allAlivePlayers;
    }
    public static void setAllAlivePlayers(Vector<Player> _allAlivePlayers) {
        allAlivePlayers = _allAlivePlayers;
    }
    public static void addPlayerToGame(Player player){
        allAlivePlayers.add(player);

        player.OnReceiveEffect.AddListener(new IEventListener<Effect>() {
            @Override
            public void Response(Effect effect) {
                OnPlayerReceiveEffect.Invoke(player, effect);
            }
        });

        player.OnChangeRole.AddListener(new IEventListener<Role>() {
            @Override
            public void Response(Role role) {
                OnPlayerChangeRole.Invoke(player, role);
            }
        });

        for (Effect effect: player.getEffects()) {
            player.OnReceiveEffect.Invoke(effect);
        }

        OnAddPlayer.Invoke(player);
    }
    public static void removePlayerFromGame(Player player){
        allAlivePlayers.remove(player);
        allDeadPlayers.remove(player);

        OnRemovePlayer.Invoke(player);
    }

    public static void removeAllPlayersFromGame(){
        allAlivePlayers.clear();
        allDeadPlayers.clear();
    }

    public static void revivePlayer(Player player){
        if(!allDeadPlayers.contains(player)) return;

        allAlivePlayers.add(player);
        allDeadPlayers.remove(player);
        player.setCurrentState(Player.PlayerState.ALIVE);
        OnRevivePlayer.Invoke(player);
    }

    public static Event2<Player, Result.KillType> OnKillPlayer;
    private static Vector<Player> allDeadPlayers;
    public static Vector<Player> getAllDeadPlayers() {
        return allDeadPlayers;
    }
    public static void setAllDeadPlayers(Vector<Player> _allDeadPlayers) {
        allDeadPlayers = _allDeadPlayers;
    }
    public static void killPlayer(Player player, Result.KillType killType){
        if(!allAlivePlayers.contains(player)) return;

        allAlivePlayers.remove(player);
        allDeadPlayers.add(player);
        player.setCurrentState(Player.PlayerState.DEAD);
        OnKillPlayer.Invoke(player,killType);
    }
    public static void killPlayer_NOEVENT(Player player, Result.KillType killType){
        allAlivePlayers.remove(player);
        allDeadPlayers.add(player);
    }

    public static Player getPlayerByID(UUID ID){
        for (Player player: allAlivePlayers) {
            if(Objects.equals(player.getID(), ID)) return player;
        }

        for (Player player: allDeadPlayers) {
            if(Objects.equals(player.getID(), ID)) return player;
        }

        return null;
    }

    public static VoidEvent OnPlayerDataUpdated;
    public static void updatePlayerByID(UUID ID, Player newPlayer){
        for (Player player : allAlivePlayers) {
            if(Objects.equals(player.getID(), ID)){
                player = newPlayer;
                OnPlayerDataUpdated.Invoke();
            }
        }

        for (Player player : allDeadPlayers) {
            if(Objects.equals(player.getID(), ID)){
                player = newPlayer;
                OnPlayerDataUpdated.Invoke();
            }
        }
    }

    public static void prepAction(Player player, Action action, Vector<Player> chosenTargets){
        Action actionToPrep = new Action();

        actionToPrep.setName(action.getName());
        actionToPrep.setWhenTOResolve(action.getWhenTOResolve());
        actionToPrep.setValidTargets(action.getValidTargets());
        actionToPrep.setConditions(action.getConditions());
        actionToPrep.setResults(action.getResults());

        actionToPrep.setTargets(chosenTargets);

        Vector<Player> initiators = new Vector<Player>();
        for (Player p: allAlivePlayers) {
            if(p.getRole() == player.getRole()){
                initiators.add(p);
            }
        }

        actionToPrep.setInitiators(initiators);

        OnActionPrepped.Invoke(actionToPrep);

        ResolvingManager.queAction(actionToPrep);
    }

    public static void Initialize(){
        allAlivePlayers = new Vector<Player>();
        allDeadPlayers = new Vector<Player>();

        OnPlayerReceiveEffect = new Event2<Player,Effect>();
        OnPlayerChangeRole = new Event2<Player,Role>();
        OnAddPlayer = new Event<Player>();
        OnRemovePlayer = new Event<Player>();
        OnRevivePlayer = new Event<Player>();
        OnKillPlayer = new Event2<Player, Result.KillType>();
        OnPlayerDataUpdated = new VoidEvent();
        OnActionPrepped = new Event<Action>();
    }

    public static void PrintSummary(){
        System.out.print("\n == Player Manager ==\n");
        System.out.print("Alive\n");
        for (Player player : allAlivePlayers) {
            player.printSummary();
        }
        if(allAlivePlayers.size() == 0) System.out.print("none\n");

        System.out.print("Dead\n");
        for (Player player : allDeadPlayers) {
            player.printSummary();
        }
        if(allDeadPlayers.size() == 0) System.out.print("none\n");
        System.out.print("\n");
    }
}
