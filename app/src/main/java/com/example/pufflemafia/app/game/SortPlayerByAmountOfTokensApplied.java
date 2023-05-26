package com.example.pufflemafia.app.game;

import androidx.annotation.NonNull;

import java.util.Comparator;

// Helper class used to sort players by their role priority
class SortPlayerByAmountOfTokensApplied implements Comparator<Player> {
    public int compare(@NonNull Player a, @NonNull Player b)
    {
        int sizeOfA = a.getAllTokensOnPlayer().size();
        int sizeOfB = b.getAllTokensOnPlayer().size();
        if(sizeOfA < sizeOfB) return 1;
        else if (sizeOfA == sizeOfB) return 0;
        else return -1;
    }
}
