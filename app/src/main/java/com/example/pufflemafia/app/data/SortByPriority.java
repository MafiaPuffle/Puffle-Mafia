package com.example.pufflemafia.app.data;

import android.util.Log;

import java.util.Comparator;

// Helper class used to sort roles by their priority
public class SortByPriority implements Comparator<Role> {
    @Override
    public int compare(Role a, Role b) {
        Log.d("SortByPriority","I ran");
        if (a.getPriority() < b.getPriority()) {
            return -1;
        } else if (a.getPriority() > b.getPriority()) {
            return 1;
        } else {
            return 0;
        }
    }
}
