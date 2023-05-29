package com.example.pufflemafia.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CustomAppCompatActivityWrapper extends AppCompatActivity {

    private ScreenLifeCycleWatcher screenLifeCycleWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screenLifeCycleWatcher = new ScreenLifeCycleWatcher();
        getLifecycle().addObserver(screenLifeCycleWatcher);
        screenLifeCycleWatcher.Setup();
    }
}
