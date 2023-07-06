package com.example.pufflemafia.app.events;

import java.util.ArrayList;
import java.util.List;


public class Event<T> {
    private List<IEventListener<T>> listeners = new ArrayList<IEventListener<T>>();

    public void AddListener(IEventListener<T> toAdd){
        listeners.add(toAdd);
    }

    public void RemoveListener(IEventListener<T> toRemove) {
        listeners.remove(toRemove);
    }

    public void Invoke(T t){
        for (IEventListener<T> listener : listeners){
            listener.Response(t);
        }
    }
}
