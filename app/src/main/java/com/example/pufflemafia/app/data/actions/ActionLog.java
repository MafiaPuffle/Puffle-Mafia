package com.example.pufflemafia.app.data.actions;

import android.util.Log;

import com.example.pufflemafia.app.game.Player;

import java.util.Vector;

public class ActionLog {

    private Vector<String> tags;
    public void addTag(String tag){
        tags.add(tag);
    }
    public void addTags(Vector<String> tags){
        this.tags.addAll(tags);
    }
    public boolean doesLogContainTag(String tagToCheck){
        for (String tag: tags) {
            if(tag == tagToCheck)
                return true;
        }

        return false;
    }
    public boolean doesLogContainAnyTag(Vector<String> tagsToCheck){
        for (String tag: tagsToCheck) {
            if(doesLogContainTag(tag))
                return true;
        }

        return false;
    }
    public boolean doesLogContainAllTags(Vector<String> tagsToCheck){
        for (String tag: tagsToCheck) {
            if(!doesLogContainTag(tag))
                return false;
        }

        return true;
    }

    private String message;
    public void addToMessage(String addendum){
        message += addendum;
    }

    public String read(){
        return message;
    }

    public ActionLog(){
        tags = new Vector<String>();
        message = "";
    }

    public void LogSummary(){
        Log.d("ActionLog", message);
    }
}
