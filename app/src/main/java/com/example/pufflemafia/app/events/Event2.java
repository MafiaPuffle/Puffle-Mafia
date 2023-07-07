package com.example.pufflemafia.app.events;

import java.util.ArrayList;
import java.util.List;


public class Event2<T,S> {
    private List<IEvent2Listener<T,S>> listeners = new ArrayList<IEvent2Listener<T,S>>();

    public void AddListener(IEvent2Listener<T,S> toAdd){
        listeners.add(toAdd);
    }

    public void RemoveListener(IEvent2Listener<T,S> toRemove) {
        listeners.remove(toRemove);
    }

    public void Invoke(T t, S s){
        for (IEvent2Listener<T,S> listener : listeners){
            listener.Response(t, s);
        }
    }
}
