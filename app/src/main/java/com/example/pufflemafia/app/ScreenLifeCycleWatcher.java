package com.example.pufflemafia.app;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public class ScreenLifeCycleWatcher implements DefaultLifecycleObserver {

    @Override
    public void onCreate(LifecycleOwner owner){
        try {
            Boolean test = AppMinimizedWatcher.appIsMinimized;
        } catch (Exception e) {
            AppMinimizedWatcher.Initialize();
        }

        AppMinimizedWatcher.RegisterScreen(this);
    }

    @Override
    public void onPause(LifecycleOwner owner){
        AppMinimizedWatcher.ScreenPaused(this);
    }

    @Override
    public void onResume(LifecycleOwner owner){
        AppMinimizedWatcher.ScreenResume(this);
    }

    @Override
    public void onStop(LifecycleOwner owner){
        AppMinimizedWatcher.UnregisterScreen(this);
    }
}
