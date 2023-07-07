package com.example.pufflemafia.app.events;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class VoidEvent {
    private List<IVoidEventListener> listeners;

    public VoidEvent(){
        listeners = new ArrayList<IVoidEventListener>();
    }

    public void AddListener(IVoidEventListener toAdd) {
        listeners.add(toAdd);
        Log.d("Custom_VoidEvent", "A new listener has been added!");
        LogAllListeners();
    }

    public void RemoveListener(IVoidEventListener toRemove) {
        listeners.remove(toRemove);
        Log.d("Custom_VoidEvent", "A listener has been removed!");
        LogAllListeners();
    }

    public void RemoveAllListeners(){
        listeners.clear();
        Log.d("Custom_VoidEvent", "All listeners have been added!");
        LogAllListeners();
    }

    public void Invoke() {
        Log.d("Custom_VoidEvent", "All listeners have been invoked!");
        LogAllListeners();
        for (IVoidEventListener listener : listeners) {
            listener.Response();
        }
    }

    private void LogAllListeners(){
        for (IVoidEventListener listener: listeners) {
            Log.d("Custom_VoidEvent", listener + "\n");
        }
    }
}
