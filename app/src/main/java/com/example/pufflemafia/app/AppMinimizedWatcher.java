package com.example.pufflemafia.app;

import androidx.lifecycle.DefaultLifecycleObserver;

import com.example.pufflemafia.app.game.SoundManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class AppMinimizedWatcher implements DefaultLifecycleObserver {

    private enum ScreenState{PAUSED, ACTIVE};
    private static Map<ScreenLifeCycleWatcher, ScreenState> allRegisteredScreens;

    public static Event<Boolean> onAppMinimize;

    public static Event<Boolean> onAppMaximize;
    public static Boolean appIsMinimized;

    public AppMinimizedWatcher(){
        //
    }

    public static void Initialize(){
        allRegisteredScreens = new HashMap<ScreenLifeCycleWatcher, ScreenState>();
        onAppMinimize = new Event<Boolean>();
        onAppMaximize = new Event<Boolean>();
        appIsMinimized = false;
    }

    public static void RegisterScreen(ScreenLifeCycleWatcher screenLifeCycleWatcher){
        allRegisteredScreens.put(screenLifeCycleWatcher, ScreenState.ACTIVE);
    }

    public static void UnregisterScreen(ScreenLifeCycleWatcher screenLifeCycleWatcher){
        allRegisteredScreens.remove(screenLifeCycleWatcher);
    }

    public static void ScreenPaused(ScreenLifeCycleWatcher screenLifeCycleWatcher){
        allRegisteredScreens.remove(screenLifeCycleWatcher);
        allRegisteredScreens.put(screenLifeCycleWatcher, ScreenState.PAUSED);
        TryToChangeState();
    }

    public static void ScreenResume(ScreenLifeCycleWatcher screenLifeCycleWatcher){
        allRegisteredScreens.remove(screenLifeCycleWatcher);
        allRegisteredScreens.put(screenLifeCycleWatcher, ScreenState.ACTIVE);
        TryToChangeState();
    }

    private static void TryToChangeState(){
        Boolean preCheckState = appIsMinimized;
        appIsMinimized = CheckIfAppIsMinimized();

        if(preCheckState != appIsMinimized){
            // we changed states
            if(appIsMinimized){
                // we minimized the app
                onAppMinimize.Invoke(true);
                SoundManager.onAppMinimize();
            }
            else{
                // we maximized the app
                onAppMinimize.Invoke(false);
                SoundManager.onAppMaximize();
            }
        }
    }

    private static boolean CheckIfAppIsMinimized(){
        for (Map.Entry<ScreenLifeCycleWatcher, ScreenState> entry: allRegisteredScreens.entrySet()) {
            if(entry.getValue() == ScreenState.ACTIVE) return false;
        }
        return true;
    }
}
