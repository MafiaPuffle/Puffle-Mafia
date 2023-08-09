package com.example.pufflemafia.app.game;

import android.util.Log;

import com.example.pufflemafia.app.data.Role;

import java.util.Comparator;

public class SortPlayByPriority implements Comparator<Player>{
    @Override
    public int compare(Player a, Player b) {
        Log.d("SortByPriority","I ran");
        if (a.getRole().getPriority() < b.getRole().getPriority()) {
            return -1;
        } else if (a.getRole().getPriority() > b.getRole().getPriority()) {
            return 1;
        } else {
            return 0;
        }
    }
}