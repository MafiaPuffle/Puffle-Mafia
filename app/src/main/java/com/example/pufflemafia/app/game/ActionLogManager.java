package com.example.pufflemafia.app.game;

import com.example.pufflemafia.app.data.actions.ActionLog;

import java.util.Vector;

public class ActionLogManager {

    private static Vector<ActionLog> logs;
    public static Vector<ActionLog> getLogs(){
        return logs;
    }
    public static void addLog(ActionLog log){
        logs.add(log);
    }
    public static void clearAllLogs(){
        logs.clear();
    }

    public static void Initialize(){
        logs = new Vector<ActionLog>();
    }

    public static void Summary(){
        for (ActionLog log: logs) {
            log.LogSummary();
        }
    }
}
