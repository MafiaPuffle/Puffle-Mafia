package com.example.pufflemafia.app;

import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.DefaultLifecycleObserver;

import com.example.pufflemafia.app.game.SoundManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class AppMinimizedWatcher implements DefaultLifecycleObserver {

    private enum ScreenState{PAUSED, ACTIVE};
    private static Map<ScreenLifeCycleWatcher, ScreenState> allRegisteredScreens;

    public static Event<Boolean> onAppMinimize;
    public static Boolean appIsMinimized;

    public static AppMinimizedWatcher instance;

    public static Intent latestIntent;

    private AppMinimizedWatcher(){
        //
    }

    public static void Initialize(){
        allRegisteredScreens = new HashMap<ScreenLifeCycleWatcher, ScreenState>();
        onAppMinimize = new Event<Boolean>();
        appIsMinimized = false;
        instance = new AppMinimizedWatcher();
    }

    public static void RegisterScreen(ScreenLifeCycleWatcher screenLifeCycleWatcher){
        if(allRegisteredScreens == null) Initialize();
        ScreenState state = ScreenState.ACTIVE;
        allRegisteredScreens.put(screenLifeCycleWatcher, state);
        latestIntent = screenLifeCycleWatcher.intent;
        Log.d("AppMinimizedWatcher","latestIntent: " + latestIntent);
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
                //SoundManager.onAppMinimize();
            }
            else{
                // we maximized the app
                onAppMinimize.Invoke(false);
                //SoundManager.onAppMaximize();
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
