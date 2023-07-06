package com.example.pufflemafia.app.events;

import java.util.ArrayList;
import java.util.List;

public class VoidEvent {
    private List<IVoidEventListener> listeners = new ArrayList<IVoidEventListener>();

    public void AddListener(IVoidEventListener toAdd) {
        listeners.add(toAdd);
    }

    public void RemoveListener(IVoidEventListener toRemove) {
        listeners.remove(toRemove);
    }

    public void Invoke() {
        for (IVoidEventListener listener : listeners) {
            listener.Response();
        }
    }
}
