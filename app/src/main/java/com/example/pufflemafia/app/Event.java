package com.example.pufflemafia.app;

import java.util.ArrayList;
import java.util.List;

public class Event<T> {
    private List<IListener<T>> listeners = new ArrayList<IListener<T>>();

    public void AddListener(IListener<T> toAdd){
        listeners.add(toAdd);
    }

    public void Invoke(){
        for (IListener<T> listener : listeners){
            listener.Response();
        }
    }

    public void Invoke(T t){
        for (IListener<T> listener : listeners){
            listener.Response(t);
        }
    }
}
