package com.example.pufflemafia.app;

import com.example.pufflemafia.app.events.IEventListener;

public class Listener<T> implements IEventListener<T> {

    public Listener(){
        // constructor
    }
    @Override
    public void Response() {
        // do something
    }

    @Override
    public void Response(T t) {
        // do something
    }
}
