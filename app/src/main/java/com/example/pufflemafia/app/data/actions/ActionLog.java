package com.example.pufflemafia.app.data.actions;

import android.util.Log;

import com.example.pufflemafia.app.game.Player;

import java.util.Vector;

public class ActionLog {

    private Vector<String> initiatorNames;

    public Vector<String> getInitiatorNames() {
        return initiatorNames;
    }

    private String actionName;

    public String getActionName() {
        return actionName;
    }

    private Vector<String> targetNames;

    public Vector<String> getTargetNames() {
        return targetNames;
    }

    private String result;
    public String getResult() {
        return result;
    }

    public ActionLog(){
        initiatorNames = new Vector<String>();
        targetNames = new Vector<String>();
    }

    public ActionLog addInitiatorName(String initiator){
        this.initiatorNames.add(initiator);
        return this;
    }

    public ActionLog setInitiatorNames(Vector<Player> initiators){
        if(initiators == null){
            this.initiatorNames.add("NULL");
            return this;
        }

        for (Player player: initiators) {
            this.initiatorNames.add(player.getName());
        }
        return this;
    }

    public ActionLog setAction(String actionName){
        this.actionName = actionName;
        return this;
    }

    public ActionLog addTargetName(String target){
        this.targetNames.add(target);
        return this;
    }

    public ActionLog setTargetNames(Vector<Player> targets){
        if(targets == null){
            this.initiatorNames.add("NULL");
            return this;
        }

        for (Player player: targets) {
            this.targetNames.add(player.getName());
        }
        return this;
    }

    public ActionLog setResult(String result){
        this.result = result;
        return this;
    }

    public String read(){
        String output = "";

        for (String name: initiatorNames) {
            output += (name + " ");
        }

        output += getResult();
        output += " used ";
        output += getActionName();
        output += " on ";

        for (String name: targetNames) {
            output += (name + " ");
        }

        return output;
    }

    public void LogSummary(){
        Log.d("ActionLog", ""
                + initiatorNames.get(0) + " " + getResult() + " used "
                + getActionName() + " on " + getTargetNames().get(0));
    }
}
