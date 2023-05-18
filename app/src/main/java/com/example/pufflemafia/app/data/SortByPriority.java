package com.example.pufflemafia.app.data;

import java.util.Comparator;

// Helper class used to sort roles by their priority
public class SortByPriority implements Comparator<Role> {
    public int compare(Role a, Role b) {
        if (a.getPriority() < b.getPriority()) return -1;
        else if (a.getPriority() == b.getPriority()) return 0;
        else return 1;
    }
}
