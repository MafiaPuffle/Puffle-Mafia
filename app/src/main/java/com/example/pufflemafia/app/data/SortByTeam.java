package com.example.pufflemafia.app.data;

import androidx.annotation.NonNull;

import java.util.Comparator;

public class SortByTeam implements Comparator<Role> {
    public int compare(@NonNull Role a, @NonNull Role b) {
        int teamA = a.getTeam().ordinal();
        int teamB = b.getTeam().ordinal();
        if (teamA > teamB) return 1;
        else if (teamA == teamB) return 0;
        else return -1;
    }
}
