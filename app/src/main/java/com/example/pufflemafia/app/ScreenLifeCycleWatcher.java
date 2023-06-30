package com.example.pufflemafia.app;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public class ScreenLifeCycleWatcher implements DefaultLifecycleObserver {

    public Intent intent;

    public void Setup(Intent intent){
        this.intent = intent;
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
